# Лабораторна робота №2. Гістограма зображення
**Мета роботи: Навчитися виконувати геометричне вирівнювання; морфологічні операції ерозії, дилатації, відкриття, замикання та застосовувати фільтри згладжування.**

## Теоретичні відомості
Гістограма - це графік розподілу півтонів зображення, в якому по горизонтальній осі представлена ​​яскравість, а по вертикалі - відносна кількість пікселів з ​​даними значенням яскравості.

Обчислення гістограми для 8-бітного напівтонового зображення може бути запрограмовано так:

```
vector <int> hist (256, 0);
for (int y = 0; y <h; y + +) {
     for (int x = 0; x <w; x + +) {
         int color = image.at <unsigned char> (y, x);
         hist [color] + +;
     }
}
```
![alt text](https://lh6.googleusercontent.com/3S6xS3sDRw-HIZZAqhtnRSZEpcybwcK0S43uOw_8qUAlOXcODBDnt1cZZzDR3K_AH4sNYEJg-DxV89-catuFbajmGmrLBGYHo3ZlMMDLB-p_Uv-nqOJEPCUWEa5WJGg9nv1AH9U)

Вихідне зоображення

Нехай буде дано 8-бітове зображення A[0;255]. Тоді шукаємо Amin по всіх x,y і Amax.

Amin=min(A(x,y)) для всіх x,y

Amax=max(A(x,y)) для всіх x,y

В результаті діапазон яскравості лінійно розтягнеться на всю область. Зображення стане більш контрастним, так як відстань між сусідніми областями яскравості збільшиться.

![alt text](https://lh4.googleusercontent.com/AwWfhfAfeyY9dAmOhC-cmfRcDLSJ98oa4t0qMPBfPI7s4d1YHTRIdN_kOhbSIcn7qA4SqX7R9iWDr-PksuiMUST4iTqaF77SrpM4bBYvpR5aa1ZnjkRCzacmP9yGA3Ea_egsIIY)

Нормалізоване зображення


![alt text](https://lh6.googleusercontent.com/c0T0bI0uiSc_-uQGNRZq1qAp3MZY6-COnzwq1hx-0dpkiOT173OrZN_Mv6nQ1r4S0C3m7QPnzZ1B5mO5JyZ66K38QpteUxipP7IqBeyaz73YK6jD1rLei4oAAxRIJP_mKlm6h7w)

Еквалізоване зоображення

Метою еквалізації є таке перетворення, щоб гістограма яскравості максимально близько відповідала рівномірному закону розподілення, тобто вихідне зображення повинно містити однакову кількість пікселів для кожного значення яскравості.



Алгоритм перетворення для 8-бітового зображення

В значення яскравості 0 на гістограмі переходять пікселі з яскравістю 0,1,2… поки їх кількість не перевищить q. Аналогічно в значення 1 переходять пікселі і(0)+1, і(0)+2…

0: 0,1,2…                 і(0)
1: і(0)+1, і(0)+2…і(1)
2: і(1)+1, і(1)+2…і(2)

Коли гістограма в діапазоні від Amin до Amax приблизно однакова результат еквалізації майже не відрізняється від нормалізації за яскравістю, інакше дозволяє виділити раніше непомітні деталі.


## Хід роботи

Створюєм новий проект.

Завантажуємо зоображення. Використовуючи готову функцію, проводимо еквалізацію зоображення.
```
Imgproc.equalizeHist();
```
![alt text](https://github.com/OTanTalum/AILabs/blob/master/AILab2/pic/%D1%96.png?raw=true)

Порівняння еквалізованого зоображення, та оригінального зображення.

![alt text](https://github.com/OTanTalum/AILabs/blob/master/AILab2/pic/%D0%91%D0%BD%D1%96.png?raw=true)

![alt text](https://github.com/OTanTalum/AILabs/blob/master/AILab2/pic/%D0%91%D1%96.png?raw=true)

Порівння гістаграми еквалізованого зоображення, та гістаграми оригіналу зоображення.

Використовуючи готову функцію, проводимо нормалізацію зоображення.
```
Core.normalize();
```
![alt text](https://github.com/OTanTalum/AILabs/blob/master/AILab2/pic/%D0%91%D0%B5%D0%B7%20%D1%96.png?raw=true)

![alt text](https://github.com/OTanTalum/AILabs/blob/master/AILab2/pic/%D0%B7%20%D1%96%D0%BC%D0%B5%D0%BD%D1%96.png?raw=true)

Нормалізоване зоображення, та гістограма нормалізованого зоображення.
