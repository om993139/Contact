# Contact  App

Contact is an Android application that allows users to manage their contacts efficiently. It supports adding, editing, and deleting contacts, as well as assigning a profile photo to each contact. The app is built using MVVM architecture with Dependency Injection and Room database. It utilizes Material3 for a modern and cohesive UI design.

## 📥 Download

You can download the latest version by clicking the button below.

[![Download](https://img.shields.io/badge/Download-Latest-blue)](https://github.com/om993139/Contact/releases/download/v1.0.0/Contacts.apk)




## ✨ Features

- **Add Contacts**: Add new contacts with name, phone number, email, and profile photo.
- **Edit Contacts**: Edit existing contacts' details.
- **Delete Contacts**: Remove contacts from the list.
- **Profile Photo**: Assign and display profile photos for each contact.
- **MVVM Architecture**: Follows MVVM architecture for a clean and maintainable codebase.
- **Room Database**: Uses Room database for local storage of contacts.
- **Material3 UI**: Modern UI components using Material3.

## 📸 Screenshots

<!-- Add your screenshots here -->
![Design1](https://github.com/user-attachments/assets/83b76f15-7997-414e-9d8c-40490f7bb90c)



![Design2](https://github.com/user-attachments/assets/ba858675-a4da-47fd-8fc9-52b97f203bad)


## 🛠 Installation

1. Clone the repository:
    ```sh
    https://github.com/om993139/Contact
    ```
2. Open the project in Android Studio.
3. Build and run the app on your Android device or emulator.






## 🚀 Usage

Home Screen
The home screen of the app displays a list of contacts with their respective details:

Contact Details Displayed: Each contact card shows the name, phone number, email, and profile photo.
Actions Available:
Add a new contact: Click the floating action button with the '+' icon.
Edit a contact: Tap on the contact card to navigate to the edit screen.
Delete a contact: Click the delete icon on the contact card.
Call a contact: Click the call icon on the contact card.






## Add/Edit Screen
The add/edit screen enables users to manage contact details:

Data Entry Fields:
Enter Name: Type the contact's name.
Enter Phone Number: Type the contact's phone number.
Enter Email: Type the contact's email address.
Add/Update Profile Photo: Click on the profile photo placeholder to choose an image from your device.
Save Contact: After entering or updating details, click the 'Save' button to save the contact.






## 🗂 Code Structure
MVVM Architecture
The app follows the MVVM (Model-View-ViewModel) architecture pattern:

Model: Represents the data and business logic. It includes Room database entities and DAOs.
ViewModel: Acts as a mediator between the View (UI) and Model. It manages UI-related data and communicates with the backend or repository.
View: The UI layer built using Jetpack Compose, which displays data and interacts with users.
Dependency Injection
Dependency injection using Hilt is utilized to manage app dependencies:

Purpose: Helps in creating a modular and testable codebase by providing dependencies to classes.
Advantages: Enhances code readability, maintainability, and scalability.
Room Database
Room is employed for local database operations:

Functionality: Provides an abstraction layer over SQLite for efficient storage and management of contacts locally.
Advantages: Enables seamless data handling operations like insertion, deletion, and updating of contact records.





## 📦 Dependencies

The app uses the following dependencies:

- **AndroidX Libraries**: For backward compatibility and modern Android development.
- **Jetpack Compose**: For building the UI.
- **Hilt**: For dependency injection.
- **Room**: For local database storage.
- **Material Components**: For modern UI components using Material3.

Add these dependencies in your `build.gradle.kts` file:
```kotlin
dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:1.1.0")
    implementation("androidx.compose.material3:material3:1.0.0-alpha01")
    implementation("com.google.dagger:hilt-android:2.40.5")
    kapt("com.google.dagger:hilt-compiler:2.40.5")
    implementation("androidx.room:room-runtime:2.4.0")
    kapt("androidx.room:room-compiler:2.4.0")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")
}


