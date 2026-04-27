# Сборка debug APK «Герой бренда».
# Требуется: Android SDK (см. local.properties), JDK 17.

$ErrorActionPreference = 'Stop'
$Root = Split-Path -Parent $PSScriptRoot
Set-Location $Root

# Корень Android Studio (как у вас на диске C:)
$AndroidStudioRoot = 'C:\Program Files\Android\Android Studio'
$StudioJbr = Join-Path $AndroidStudioRoot 'jbr'

$Temurin = Get-ChildItem 'C:\Program Files\Eclipse Adoptium' -Directory -Filter 'jdk-17*' -ErrorAction SilentlyContinue |
    Sort-Object Name -Descending |
    Select-Object -First 1

if (Test-Path "$StudioJbr\bin\java.exe") {
    $env:JAVA_HOME = $StudioJbr
} elseif ($Temurin) {
    $env:JAVA_HOME = $Temurin.FullName
} else {
    Write-Error "Не найден JDK 17. Ожидался JBR: $StudioJbr. Установите Android Studio или: winget install EclipseAdoptium.Temurin.17.JDK -e"
}

$env:ANDROID_HOME = Join-Path $env:LOCALAPPDATA 'Android\Sdk'
$env:ANDROID_SDK_ROOT = $env:ANDROID_HOME
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

Write-Host "JAVA_HOME=$env:JAVA_HOME"
Write-Host "ANDROID_HOME=$env:ANDROID_HOME"
& .\gradlew.bat assembleDebug --no-daemon
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

$Apk = Join-Path $Root 'app\build\outputs\apk\debug\app-debug.apk'
$Out = Join-Path $Root 'GeroyBranda-debug.apk'
Copy-Item -Path $Apk -Destination $Out -Force
Write-Host "Готово: $Out"
