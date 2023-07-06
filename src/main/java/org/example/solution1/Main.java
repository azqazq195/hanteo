package org.example.solution1;

import org.example.solution1.category.Category;
import org.example.solution1.category.CategoryDataStructure;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 제공된 첨부파일의 데이터
        System.out.println("Sample 데이터 테스트");
        testSample();
        System.out.println();

        System.out.println("커스텀 데이터 테스트");
        testCustom();
        System.out.println();
    }

    public static void testSample() {
        List<Category> categories;
        Category category;
        String jsonText;

        CategoryDataStructure data = new CategoryDataStructure();
        // Sample Data 생성
        data.createSample();

        category = data.getOneByName("남자");
        jsonText = data.getJsonText(category);
        System.out.println(jsonText);

        category = data.getOneByName("익명게시판");
        jsonText = data.getJsonText(category);
        System.out.println(jsonText);

        categories = data.getAllByName("공지사항");
        jsonText = data.getJsonText(categories);
        System.out.println(jsonText);

        try {
            data.getOneByName("엑스");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        data.clear();
        try {
            data.getOneByName("남자");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void testCustom() {
        List<Category> categories;
        Category category;
        String jsonText;

        CategoryDataStructure data = new CategoryDataStructure();
        // custom 데이터 생성
        data.createCustom();

        category = data.getOneByName("유니크");
        jsonText = data.getJsonText(category);
        System.out.println(jsonText);
    }
}