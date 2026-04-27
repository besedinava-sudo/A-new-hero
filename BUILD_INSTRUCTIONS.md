# Сборка APK «Герой бренда»

## Требования

- [Android Studio](https://developer.android.com/studio) Koala (2024.1.1) или новее (рекомендуется).
- JDK 17 (обычно встроен в Android Studio: **Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK**).

## Где установлена Android Studio (ваш ПК)

**У вас зафиксирован такой путь:** корень Android Studio —

`C:\Program Files\Android\Android Studio`

Встроенный JDK для Gradle и терминала (JBR) лежит здесь:

`C:\Program Files\Android\Android Studio\jbr`

Если Studio установите в другую папку — замените оба пути в инструкции и в `scripts\build-debug.ps1` (переменная `$AndroidStudioRoot`).

В Android Studio путь к SDK смотрите в **Settings → Languages & Frameworks → Android SDK** (поле **Android SDK Location**).

## Android Studio

1. Откройте папку проекта: **File → Open** → выберите каталог с файлом `settings.gradle.kts`.
2. Дождитесь синхронизации Gradle (**Sync Now** при появлении баннера).
3. При первом запуске Studio может скачать Gradle и зависимости — это нормально.
4. Соберите APK:
   - **Build → Build Bundle(s) / APK(s) → Build APK(s)**  
   - либо в терминале Android Studio:  
     `./gradlew assembleDebug` (macOS/Linux) или `gradlew.bat assembleDebug` (Windows).
5. Готовый файл:  
   `app/build/outputs/apk/debug/app-debug.apk`  
   Для релизной подписи используйте **Build → Generate Signed Bundle / APK** и свой keystore.

## Cursor (терминал)

1. Установите Android SDK (через Android Studio: **SDK Manager**) и задайте переменную окружения `ANDROID_HOME` (или `ANDROID_SDK_ROOT`) на путь к SDK.
2. В корне проекта создайте файл `local.properties` со строкой (подставьте свой путь к SDK):

   ```properties
   sdk.dir=C\:\\Users\\ВАШ_ПОЛЬЗОВАТЕЛЬ\\AppData\\Local\\Android\\Sdk
   ```

   На Windows обратные слэши в пути экранируются вторым слэшем, как в примере.

3. Если в проекте ещё нет обёртки Gradle, один раз откройте проект в Android Studio — она появится автоматически — или выполните в каталоге с установленным Gradle:  
   `gradle wrapper --gradle-version 8.9`
4. В терминале Cursor из корня проекта (пример для вашей установки Studio):

   ```powershell
   $env:JAVA_HOME = 'C:\Program Files\Android\Android Studio\jbr'
   $env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"
   .\gradlew.bat assembleDebug
   ```

   Результат тот же: `app\build\outputs\apk\debug\app-debug.apk`.

## Запуск на устройстве

- Подключите телефон с **режимом разработчика** и **USB-отладкой**, нажмите **Run** (зелёный треугольник) в Android Studio, выберите устройство.

## Примечания

- **minSdk 26** (Android 8.0).
- Данные героев хранятся только в локальной базе Room на устройстве.

## Уже настроено в этом копии проекта

- Для сборки из терминала можно использовать **JDK 17 (Eclipse Temurin)** — установка одной командой:  
  `winget install EclipseAdoptium.Temurin.17.JDK -e --accept-package-agreements --accept-source-agreements`  
  Типичный путь: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`.
- В профиле Windows пользователя могут быть заданы **`JAVA_HOME`** и **`ANDROID_HOME`** / **`ANDROID_SDK_ROOT`** (после настройки окружения ассистентом) — в новых окнах PowerShell/CMD Gradle найдёт Java и SDK без ручного экспорта.
- Скрипт **`scripts\build-debug.ps1`** — выставляет `JAVA_HOME`: сначала **JBR** из `C:\Program Files\Android\Android Studio\jbr`, если его нет — **Temurin 17** из `Eclipse Adoptium`; затем `ANDROID_HOME`, `gradlew assembleDebug` и копия APK в **`GeroyBranda-debug.apk`**.

- В корне есть **`gradlew.bat`** / **`gradlew`** и **`gradle/wrapper/`** — можно собирать без отдельной установки Gradle.
- Файл **`local.properties`** указывает на SDK в `%LOCALAPPDATA%\Android\Sdk` (UTF-8 **без BOM**, иначе Gradle может не увидеть `sdk.dir`).
- В **`gradle.properties`** включено **`android.overridePathCheck=true`**, если путь к проекту содержит **не-ASCII** символы (например, папка «Курсор») — иначе сборка на Windows может останавливаться AGP.
- Для сборки из терминала задайте **`JAVA_HOME`** на JBR из вашей Android Studio:  
  **`C:\Program Files\Android\Android Studio\jbr`**  
  (корень IDE: **`C:\Program Files\Android\Android Studio`**)
- После успешной сборки копия APK также лежит в корне проекта: **`GeroyBranda-debug.apk`** (дубликат `app-debug.apk`).
