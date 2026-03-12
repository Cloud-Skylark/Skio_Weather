<div align="center">

# ☁️ S K I O
### Weather, Simplified.

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple?style=for-the-badge&logo=kotlin)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-blue?style=for-the-badge&logo=android)
![Retrofit](https://img.shields.io/badge/Networking-Retrofit2-green?style=for-the-badge&logo=square)

<br />

**Skio** is a proof-of-concept weather application designed with one goal: **Zero Clutter.**
Built to demonstrate how complex networking can power a serene, minimalistic user interface.

[View Screenshots](#-visual-tour) • [Tech Architecture](#-under-the-hood) • [Download](#-installation)

</div>

---

## 💎 The Philosophy

**Skio** was built during an intensive study of Android networking protocols. The challenge was to take raw, complex JSON data from open-source weather APIs and render it into a UI that feels weightless.

It strips away the noise of traditional weather apps—no ads, no radar clutter, just the data you need: **Temperature, Condition, and Forecast.**

## 📸 Visual Tour

<img width="200" height="892" alt="skio" src="https://github.com/user-attachments/assets/48beaef3-0a1d-4840-bc50-a1fd9b3e51af" />


---

## ⚡ Under the Hood

While the UI is minimal, the engineering is robust. **Skio** serves as a practical implementation of advanced networking in Android.

### 🔌 Networking (Retrofit 2)
Skio utilizes **Retrofit 2** to handle API communications.
* **Efficient Parsing:** Uses GSON/Moshi to convert complex JSON responses into clean Kotlin Data Classes.
* **Error Handling:** Custom interceptors manage network timeouts and bad responses gracefully, ensuring the app never crashes on a bad connection.
* **Async Data:** Network calls are executed off the main thread using **Kotlin Coroutines**, ensuring the UI remains buttery smooth (60fps).

### 🏗 Architecture (Clean MVVM)
The codebase is strictly organized to decouple logic from the view:
1.  **Presentation:** Jetpack Compose (State-driven UI).
2.  **Domain:** Pure business logic (Use Cases).
3.  **Data:** Repository implementation and Remote Data Sources.

---

## 🛠️ Stack Overview

* **Core:** Kotlin
* **Interface:** Jetpack Compose (Material Design 3)
* **API Client:** Retrofit 2 + OkHttp
* **Dependency Injection:** Hilt / Manual DI
* **Image Loading:** Coil (Async loading for weather icons)

---

## 🚀 Installation & Setup

1.  **Clone the Repo**
    ```bash
    git clone [https://github.com/badalahire/skio-weather.git](https://github.com/badalahire/skio-weather.git)
    ```
2.  **Configure API Key**
    * Skio uses the OpenWeatherMap API.
    * Create a `secrets.properties` file or add to `local.properties`:
        ```properties
        WEATHER_API_KEY="your_api_key_here"
        ```
3.  **Build**
    * Sync Gradle and run on Pixel Emulator (API 30+ recommended).

---

<div align="center">

**Developed by Badal Ahire**
<br>
*Android Developer • BCA Student • Nashik, India*



</div>
