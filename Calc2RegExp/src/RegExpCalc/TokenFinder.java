package RegExpCalc;

//вход - строка
//выход - токены или ошибка

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenFinder {
    private String inputString;
    String[] tokenArray= new String[4];

    public TokenFinder(String inputString) {
        this.inputString = inputString;
    }

    public String[] getTokens() throws Exception {
        //задаем паттерн для поиска
        Pattern pattern=Pattern.compile("(\".{1,10}\") ([+|*|\\-|/]) (\\d{1,2}|\".{1,10}\")");
        Matcher matcher=pattern.matcher(inputString);
        if (matcher.matches()){
            //если строка в нужном нам формате то сохраняем данные из групп в масив токенов
            for (int i=1; i<=3; i++) {
                tokenArray[i]=matcher.group(i);
            }
        } else {
            throw new Exception("Не верный формат данных");
        }
        return tokenArray;
    }
}
