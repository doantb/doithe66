package vn.doithe66.doithe66.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.doithe66.doithe66.model.BankType;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.UserInfo;

/**
 * Created by Windows 10 Now on 2/3/2018.
 */

public class ConfigJson {
    private static Gson gson = new Gson();

    public String setToString(ArrayList<String> textList) {
        return gson.toJson(textList);
    }

    public List<String> getArray(String textList) {
        String[] arr = gson.fromJson(textList, String[].class);
        List<String> wordList = Arrays.asList(arr);
        return wordList;
    }

    public static String setListTypeCard(ArrayList<ItemCard> textList) {
        return gson.toJson(textList);
    }

    public static List<ItemCard> getListTypeCard(String textList) {
        Type type = new TypeToken<List<ItemCard>>() {
        }.getType();
        List<ItemCard> itemCardList = gson.fromJson(textList, type);
        return itemCardList;
    }

    public static String setListBankType(ArrayList<BankType> textList) {
        return gson.toJson(textList);
    }

    public static List<BankType> getListBankType(String textList) {
        Type type = new TypeToken<List<BankType>>() {
        }.getType();
        List<BankType> bankTypes = gson.fromJson(textList, type);
        return bankTypes;
    }

    public static String setUserAccount(UserInfo userInfo) {
        return gson.toJson(userInfo);
    }

    public static UserInfo getUserAccount(String textList) {
        if (textList != null) {
            Type type = new TypeToken<UserInfo>() {
            }.getType();
            UserInfo userInfo = gson.fromJson(textList, type);
            return userInfo;
        }
        return new UserInfo();
    }
}
