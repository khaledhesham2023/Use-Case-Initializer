# Use-Case Initalizer
Talk-To-ChatGPT is an application which enables the user to record a voice record and sends it to OpenAI in order to send the proper answer in the form of AI-voiced record.

![WhatsApp Image 2024-03-17 at 2 52 37 PM](https://github.com/khaledhesham2023/Talk-to-ChatGPT/assets/95777100/97db21db-476e-4e6f-80ad-dad77533189a)
![WhatsApp Image 2024-03-17 at 2 52 37 PM (2)](https://github.com/khaledhesham2023/Talk-to-ChatGPT/assets/95777100/841b7ef4-2272-4c76-b36d-df130cf2b411)
![WhatsApp Image 2024-03-17 at 2 52 37 PM (1)](https://github.com/khaledhesham2023/Talk-to-ChatGPT/assets/95777100/baaa8e73-6a16-443a-90fd-ea84876e07d8)

# How does it work?
## Step 1: Creating a voice record for the user:
The user clicks on the record button and once he stops the recording, the voice file is saved on a temp file on the device.
## Step 2: OpenAI stage:
The user's voice file is sent to a Spring-Boot backend system that in turn connects to OpenAI.
The voice file is transcribed into the suitable text according to the user's language. Then, the backend system sends the text once again to OpenAI to process the text and sends the suitable answer as a text to the user's device. 
Next the user's device sends the text once again to OpenAI to convert answer text to an AI-voiced file to play in the app.
## Step 3: Saving the question and its answer:
Before sending the answer voice file to the user, The Spring-Boot application, logs the outputs of the three OpenAI MySQL Database as well as uploading the voice files into MinIO Bucket Object and the records are then logged into a Kafka Topic.
## Step 4: Retrieving the questions and their answers to the app:
And a list of previously asked questions and their answers are uploaded on a list into the user's screen.

# Libraries and technologies:
### Android:
Android Framework, Android Studio Hedgehog IDE, Kotlin programming language.
Retrofit for network connections, Kotlin Coroutines for multithreading purposes, Android App Architecture such as MVVM, Dependency Injection using Dagger Hilt.
### Backend System:
Spring-Boot Framework, Intellij IDEA IDE, Java programming language.
Apache Kafka, MinIO Object Storage.
[https://github.com/khaledhesham2023/Use-Case-Initializer-backend]
### DBMS:
MySQL Server Framework and SQL Query language.
### Docker:
for quick running,testing and deploying the Spring-Boot application as a container using docker desktop.
