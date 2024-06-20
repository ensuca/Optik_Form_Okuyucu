ENGLISH
# Mobile Optical Reader Application Project

This project involves the development of a mobile application for reading optical mark recognition (OMR) sheets using Java and OpenCV integrated with Android Studio. The application aims to facilitate the evaluation process of OMR sheets digitally, thereby saving time and effort.

## Table of Contents
- [Introduction](#introduction)
- [Project Design](#project-design)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributors](#contributors)
- [License](#license)

## Introduction

The purpose of this project is to digitally process optical answer sheets, eliminating the need for manual or mechanical optical readers. This project aims to enhance communication between students and teachers and expedite the feedback process, thereby contributing significantly to the field of education by making test evaluation processes more efficient.

## Project Design

### Optical Forms Used
The project uses specific optical forms that are designed for ease of reading by the mobile application. The application can also recognize and process custom optical forms defined by the user.

### Algorithm Workflow
The algorithm processes the optical forms by reading the marked answers and evaluating them against the correct answers. The workflow involves:
1. Capturing the image of the OMR sheet.
2. Pre-processing the image to enhance readability.
3. Detecting and interpreting the marked answers.
4. Comparing the answers with the correct responses.
5. Generating a report of the results.

### User Interface Design
The application provides a user-friendly interface for both teachers and students. Teachers can manage classes, students, and exams, and generate reports based on the exam results.

## Technologies Used
- **Java**: For core application logic and algorithms.
- **OpenCV**: For image processing and analysis.
- **Android Studio**: For developing the mobile application interface.

### Advantages of Using Java for Mobile Applications
- **Platform Independence**: Java code can run on any platform that supports JVM.
- **Rich Library and API Support**: Extensive standard libraries and APIs facilitate quick integration of various functions.
- **Security**: Strong security features including memory management and type checking.
- **Community and Support**: A large and active developer community providing extensive resources and support.
- **Performance**: High performance through Just-In-Time (JIT) compilation and other optimization techniques.
- **Android Compatibility**: Android is largely built on Java, making it a natural choice for Android app development.

## Installation

To install and set up the project on your local machine:

1. Clone the repository:
    ```sh
    git clone https://github.com/ensuca/Optik_Form_Okuyucu.git
    ```
2. Open the project in Android Studio.
3. Build the project to download the necessary dependencies.
4. Run the application on an Android emulator or a physical device.

## Usage

1. Open the application and log in with your credentials.
2. Navigate through the menu to manage classes, students, and exams.
3. Use the camera feature to capture OMR sheets.
4. Process and evaluate the captured OMR sheets.
5. Generate and view reports of the exam results.

## Contributors

Enes UCA

## License

This project is licensed under the MIT License 

TURKISH
# Mobil Optik Okuyucu Uygulama Projesi

Bu proje, Java ve OpenCV kullanarak Android Studio ile entegre edilen optik işaret tanıma (OMR) formlarını okuyan bir mobil uygulama geliştirmeyi içermektedir. Uygulamanın amacı, OMR formlarını dijital olarak değerlendirerek zaman ve çaba tasarrufu sağlamaktır.

## İçindekiler
- [Giriş](#giriş)
- [Proje Tasarımı](#proje-tasarımı)
- [Kullanılan Teknolojiler](#kullanılan-teknolojiler)
- [Özellikler](#özellikler)
- [Kurulum](#kurulum)
- [Kullanım](#kullanım)
- [Katkıda Bulunanlar](#katkıda-bulunanlar)
- [Lisans](#lisans)

## Giriş

Bu projenin amacı, optik cevap kağıtlarını dijital olarak işleyerek manuel veya mekanik optik okuyuculara olan ihtiyacı ortadan kaldırmaktır. Bu proje, öğrenciler ve öğretmenler arasındaki iletişimi kolaylaştırarak geri bildirim sürecini hızlandırmayı ve test değerlendirme süreçlerini daha verimli hale getirmeyi hedeflemektedir.

## Proje Tasarımı

### Kullanılan Optik Formlar
Proje, mobil uygulama tarafından kolayca okunabilen belirli optik formlar kullanmaktadır. Uygulama ayrıca, kullanıcı tarafından tanımlanan özel optik formları da tanıyıp işleyebilir.

### Algoritmanın İşleyişi
Algoritma, işaretlenmiş cevapları okuyup doğru cevaplarla karşılaştırarak optik formları işler. İşleyiş süreci şunları içerir:
1. OMR formunun görüntüsünü yakalama.
2. Görüntüyü okunabilirliği artırmak için ön işleme tabi tutma.
3. İşaretlenmiş cevapları tespit etme ve yorumlama.
4. Cevapları doğru yanıtlarla karşılaştırma.
5. Sonuçların bir raporunu oluşturma.

### Kullanıcı Arayüz Tasarımı
Uygulama, hem öğretmenler hem de öğrenciler için kullanıcı dostu bir arayüz sunmaktadır. Öğretmenler sınıfları, öğrencileri ve sınavları yönetebilir ve sınav sonuçlarına dayalı raporlar oluşturabilir.

## Kullanılan Teknolojiler
- **Java**: Çekirdek uygulama mantığı ve algoritmalar için.
- **OpenCV**: Görüntü işleme ve analizi için.
- **Android Studio**: Mobil uygulama arayüzünü geliştirmek için.

### Mobil Uygulamalar İçin JAVA Kullanmanın Avantajları
- **Platform Bağımsızlığı**: Java kodu, JVM'yi destekleyen herhangi bir platformda çalışabilir.
- **Geniş Kütüphane ve API Desteği**: Geniş standart kütüphaneler ve API'ler, çeşitli işlevlerin hızlı bir şekilde entegrasyonunu sağlar.
- **Güvenlik**: Bellek yönetimi ve tür kontrolü gibi güçlü güvenlik özellikleri.
- **Topluluk ve Destek**: Geniş ve aktif bir geliştirici topluluğu, geniş kaynaklar ve destek sağlar.
- **Performans**: Just-In-Time (JIT) derlemesi ve diğer optimizasyon teknikleri sayesinde yüksek performans.
- **Android Uyumluluğu**: Android büyük ölçüde Java üzerine kuruludur, bu da Android uygulama geliştirme için doğal bir seçimdir.

## Kurulum

Projeyi yerel makinenize kurmak ve ayarlamak için:

1. Depoyu klonlayın:
    ```sh
    git clone https://github.com/ensuca/Optik_Form_Okuyucu.git
    ```
2. Projeyi Android Studio'da açın.
3. Gerekli bağımlılıkları indirmek için projeyi derleyin.
4. Uygulamayı bir Android emülatöründe veya fiziksel cihazda çalıştırın.

## Kullanım

1. Uygulamayı açın ve kimlik bilgilerinizle giriş yapın.
2. Menü aracılığıyla sınıfları, öğrencileri ve sınavları yönetin.
3. OMR formlarını yakalamak için kamera özelliğini kullanın.
4. Yakalanan OMR formlarını işleyip değerlendirin.
5. Sınav sonuçlarının raporlarını oluşturup görüntüleyin.

## Katkıda Bulunanlar

- Enes UCA- 


## Lisans

Bu proje MIT Lisansı altında lisanslanmıştır
