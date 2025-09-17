# WeaCast

A modern Android weather application that displays current and saved weather data using device location or a default location if permissions are denied.

## Tech Stack
- **Kotlin**
- **Jetpack Compose** (UI)
- **Hilt** (Dependency Injection)
- **Room** (Local Database)
- **Retrofit** (Networking)
- **Coroutines & Flow** (Async/Reactive)
- **Material3** (Design)

## Architecture
- **MVVM (Model-View-ViewModel)**
- **Repository Pattern**
- **Unidirectional Data Flow**
- **Dependency Injection with Hilt**

### Main Layers
- **Presentation**: Jetpack Compose UI, ViewModels
- **Domain**: Use Cases, Business Logic
- **Data**: Repositories, Room Database, Retrofit API

## Getting Started

### Prerequisites
- Android Studio Hedgehog or newer
- JDK 17+
- Internet connection (for weather API)

### Setup
1. **Clone the repository:**
   ```sh
   git clone <your-repo-url>
   cd WeatherApp
   ```
2. **Create `secret.gradle.kts` in the project root:**
   This file should contain your API key and base URL for the weather API.

   Example `secret.gradle.kts`:
   ```kotlin
   extra["WEATHER_API_KEY"] = "your_api_key_here"
   extra["WEATHER_BASE_URL"] = "https://api.openweathermap.org/data/2.5/"
   ```
   Replace `your_api_key_here` with your actual OpenWeatherMap API key.

3. **Sync Gradle:**
   Open Android Studio and let it sync the project.

4. **Run the app:**
   Select a device or emulator and click Run.

## How to Create `secret.gradle.kts`
1. In the root of your project (same level as `build.gradle.kts`), create a file named `secret.gradle.kts`.
2. Add the following content:
   ```kotlin
   extra["WEATHER_API_KEY"] = "your_api_key_here"
   extra["WEATHER_BASE_URL"] = "https://api.openweathermap.org/data/2.5/"
   ```
3. Do **not** commit this file to version control. Add it to your `.gitignore`.

## Features
- Current weather using device location (with runtime permission handling)
- Fallback to default location if permission denied
- Saved weather data with bottom navigation
- Modern Material3 UI
- Hilt DI, Room, Retrofit, and more

---

For more details, see the code and comments in each module.

