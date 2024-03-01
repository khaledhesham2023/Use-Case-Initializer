package com.example.speechtotextandanswerapp.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speechtotextandanswerapp.R
import com.example.speechtotextandanswerapp.base.BaseFragment
import com.example.speechtotextandanswerapp.databinding.FragmentMainBinding
import com.example.speechtotextandanswerapp.ui.model.Message
import com.example.speechtotextandanswerapp.ui.model.Question
import com.example.speechtotextandanswerapp.ui.model.request.ChatRequest
import com.example.speechtotextandanswerapp.ui.model.request.QuestionRequest
import com.example.speechtotextandanswerapp.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Random


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
    override val layout: Int
        get() = R.layout.fragment_main
    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    private lateinit var chatList: ArrayList<Question>
    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var gptMessages: ArrayList<Message>
    private var isPlaying = false
    private val REQUEST_CODE = 200
    private var mediaRecorder: MediaRecorder? = null
    private lateinit var askedQuestion: String
    private lateinit var savedFile: File

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatList = ArrayList()
        gptMessages = ArrayList()
        questionsAdapter = QuestionsAdapter(chatList)
        getMicrophonePermission()
        viewBinding.answers.adapter = questionsAdapter
        viewBinding.answers.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getQuestions()
        setupListeners()
        setupObservers()
    }


    @SuppressLint("SetTextI18n")
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @RequiresApi(Build.VERSION_CODES.S)
    private fun setupListeners() {
        viewBinding.record.setOnClickListener {
            isPlaying = !isPlaying
            if (isPlaying) {
                setupRecordingFile()
                viewBinding.clickToRecord.text = getString(R.string.speak_now)
                viewBinding.record.setImageResource(R.drawable.ic_record)
                configureMediaRecorder()
            } else {
                viewBinding.clickToRecord.text = getString(R.string.click_on_record_button_to_record_voice)
                viewBinding.record.setImageResource(R.drawable.ic_mic)
                releaseMediaRecorder()
                val requestFile = RequestBody.create(MultipartBody.FORM, savedFile)
                val body = MultipartBody.Part.createFormData("file", savedFile.name, requestFile)
                viewModel.getSpeechResponse(
                    body,
                    MultipartBody.Part.createFormData("model", "whisper-1")
                )
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun setupObservers() {
        viewModel.getChatResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.Loading -> {
                    loadingDialog.show()
                }

                is ViewState.Success -> {
                    viewModel.saveQuestion(
                        QuestionRequest(
                            askedQuestion,
                            it.data?.get(0)?.content,
                            savedFile.name
                        )
                    )
                    loadingDialog.dismiss()

                }

                is ViewState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
            }
        })
        viewModel.getQuestionsLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.Loading -> {
                    loadingDialog.show()
                }

                is ViewState.Success -> {
                    questionsAdapter.updateDataSet(it.data)
                    loadingDialog.dismiss()
                }

                is ViewState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
            }
        })
        viewModel.saveQuestionLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.Loading -> {
                    loadingDialog.show()
                }

                is ViewState.Success -> {
                    viewModel.getQuestions()
                    loadingDialog.dismiss()
                }

                is ViewState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
            }
        })
        viewModel.getSpeechResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.Loading -> {
                    loadingDialog.show()
                }

                is ViewState.Success -> {
                    askedQuestion = it.data.text!!
                    gptMessages.add(Message(content = it.data.text))
                    viewModel.getChatResponse(ChatRequest(messages = gptMessages))
                    loadingDialog.dismiss()
                }

                is ViewState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    loadingDialog.dismiss()
                }
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO),
                REQUEST_CODE
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setupRecordingFile() {
        val contextWrapper = ContextWrapper(requireContext())
        val recDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_RECORDINGS)
        savedFile = File(recDirectory, generateFileName())
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun configureMediaRecorder() {
        mediaRecorder = MediaRecorder()
        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder!!.setOutputFile(savedFile)
        mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder!!.prepare()
        mediaRecorder!!.start()
    }

    private fun releaseMediaRecorder() {
        mediaRecorder!!.stop()
        mediaRecorder!!.release()
        mediaRecorder = null
    }

    private fun generateFileName():String{
        return "${Date().time}${Random().nextInt(1000000000)}.mp3"
    }
}