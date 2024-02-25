// #include <Windows.h>

// int main() {
//     // Replace "path\\to\\your\\soundfile.wav" with the actual path to your sound file on Windows
//     LPCWSTR soundFilePath = L"E:\\Backup phone\\mayur m13\\Samsung\\Music\\Over_the_Horizon.mp3";

//     // Play the sound asynchronously
//     PlaySound(soundFilePath, NULL, SND_FILENAME | SND_ASYNC);

//     // Wait for the sound to finish playing (optional)
//     Sleep(5000);  // Sleep for 5000 milliseconds (adjust as needed)

//     return 0;
// }
#include <Windows.h>

int wmain() {  // Use wmain for Unicode character set
    LPCWSTR soundFilePath = L"E:\\Backup phone\\mayur m13\\Samsung\\Music\\Over_the_Horizon.mp3";

    // Play the sound asynchronously
    PlaySound(soundFilePath, NULL, SND_FILENAME | SND_ASYNC);

    // Wait for the sound to finish playing (optional)
    Sleep(5000);  // Sleep for 5000 milliseconds (adjust as needed)

    return 0;
}
