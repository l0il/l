package RegExpCalc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class RegExpCalc {
    private static int intToken3;
    private static String token1;
    private static String token2;
    private static String strToken3;
    private static String strResult;

    public static void main(String[] args) {
        out.println("Строковый калькулятор");
        out.println("Введите числа:");
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            String inputString=null;
            while ((inputString=bufferedReader.readLine()) != null) {//что бы программа незаканчивалась
                TokenFinder tokenFinder=new TokenFinder(inputString);
                String[] tokensArray=tokenFinder.getTokens();
                token1=tokensArray[1].substring(1,tokensArray[1].length()-1);//избаляемся от кавычек
                token2=tokensArray[2];
                //если последний элемент строки число то конвертируем токен3 в число
                if (inputString.charAt(inputString.length()-1) != 34) {
                    //out.println("Строка не заканчивается кавычками");
                    //заначит последний элемент = числу
                    intToken3=Integer.parseInt(tokensArray[3]);//преобразуем токен3 в число
                    if (intToken3<1 || intToken3>10) {
                        throw new Exception("Неверный диапазон входных данных ");
                    }
                    out.println("Ответ = " + intOperation());//выполняем уножение/деление на число
                } else { //заначит последний элемент = строке
                    strToken3=tokensArray[3].substring(1,tokensArray[3].length()-1);//избаляемся от кавычек;
                    out.println("Ответ = " + strOperation()); //выполняем  сложение/вычитание
                }
                //bufferedReader.close();
            }
            bufferedReader.close();
        } catch (Exception e) {
            out.println("Обработка исключения");
            out.println(e.getMessage());
            //break; поставлю когда будет цикл//что бы было завершение работы программы
        }

    }

    //выполняем  сложение/вычитание
    private static String strOperation() throws Exception {
        switch (token2) {
            case "+":
                strResult = token1+strToken3;
                break;
            case "-":
                if (token1.contains(strToken3)) {//если токен3 содержится в токен1 - удаляем
                    strResult = token1.replaceAll(strToken3, "");
                } else { //в противном случае возвращаем исходную строку
                    strResult = token1;
                }
                break;
            default:
                throw new Exception("Недопустимая операция");
                //break;
        }

        return "\""+strResult+"\"";
    }

    //выполняем уножение/деление на число - результат=строка
    private static String intOperation() throws Exception {
        switch (token2) {
            case "/":
                int newLength=token1.length()/intToken3;//сокращаем длину токена1
                strResult = token1.substring(0,newLength);
                break;
            case "*":
                strResult = token1.repeat(intToken3);
                break;
            default:
                throw new Exception("Недопустимая операция");
                //break;
        }

        if (strResult.length()>40) {
            strResult=strResult.substring(0,39)+"...";
        }

        return "\""+strResult+"\"";
    }
}
