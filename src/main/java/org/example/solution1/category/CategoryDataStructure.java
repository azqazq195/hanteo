package org.example.solution1.category;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDataStructure {

    private final Map<Integer, Category> indexMap = new HashMap<>();
    private final Map<String, List<Category>> nameMap = new HashMap<>();
    private final Map<Integer, List<CategoryRelation>> relationMap = new HashMap<>();

    /**
     * name 이름의 카테고리를 생성합니다.
     *
     * @param name 카테고리 이름
     * @return 생성된 카테고리
     */
    public Category create(String name) {
        Category category = new Category(name);
        save(category);
        return category;
    }

    /**
     * name 이름의 카테고리를 생성하여 parentCategory 의 하위 관계로 추가합니다.
     *
     * @param name           카테고리 이름
     * @param parentCategory 상위 카테고리
     * @return 생성된 카테고리
     */
    public Category create(Category parentCategory, String name) {
        Category category = new Category(name);
        addRelation(parentCategory, category);
        save(category);
        return category;
    }

    /**
     * 상위 카테고리 하위에 하위 카테고리 관계를 추가합니다.
     *
     * @param parent 상위 카테고리
     * @param child  하위 카테고리
     */
    public void addRelation(Category parent, Category child) {
        CategoryRelation relation = new CategoryRelation(parent.getId(), child.getId());

        List<CategoryRelation> relationList = relationMap.getOrDefault(parent.getId(), new ArrayList<>());
        relationList.add(relation);
        relationMap.put(parent.getId(), relationList);
    }

    /**
     * 카테고리 이름으로 카테고리들을 가져옵니다.
     * <p>
     * 카테고리 이름은 중복을 허용하므로 List 로 반환됩니다.
     *
     * @param name 카테고리 이름
     * @return 해당 이름을 가진 카테고리들을 반환합니다.
     * @throws RuntimeException 해당 이름을 가진 카테고리가 존재하지 않는 경우 발생합니다.
     */
    public List<Category> getAllByName(String name) {
        List<Category> categoryList = nameMap.get(name);
        if (categoryList == null || categoryList.size() == 0) {
            throw new RuntimeException("[" + name + "] 이름의 카테고리는 존재하지 않습니다.");
        }
        return categoryList;
    }

    /**
     * 카테고리 이름으로 카테고리를 가져옵니다.
     *
     * @param name 카테고리 이름
     * @return 해당 이름을 가진 카테고리를 반환합니다.
     * @throws RuntimeException 해당 이름을 가진 카테고리가 여러개인 경우 발생합니다.
     */
    public Category getOneByName(String name) {
        List<Category> categoryList = getAllByName(name);
        if (categoryList.size() > 1) {
            throw new RuntimeException("[" + name + "] 이름의 카테고리가 여러개 입니다.");
        }
        return categoryList.get(0);
    }

    /**
     * 카테고리 id 로 카테고리를 가져옵니다.
     *
     * @param id 카테고리 id
     * @return 해당 id 를 가진 카테고리를 반환합니다. 존재 하지 않는 경우 Exception 이 발생합니다.
     * @throws RuntimeException 해당 id 를 가진 카테고리가 존재하지 않는 경우 발생합니다.
     */
    public Category getById(int id) {
        Category category = indexMap.get(id);
        if (category == null) {
            throw new RuntimeException("[" + id + "] 식별자 카테고리는 존재하지 않습니다.");
        }
        return category;
    }

    /**
     * 카테고리 저장공간 및 카테고리 id를 초기화 합니다.
     */
    public void clear() {
        indexMap.clear();
        nameMap.clear();
        relationMap.clear();
        CategoryIdGenerator.getInstance().clear();
    }

    /**
     * 전달된 카테고리를 포함한 하위 카테고리 관계를 json format 으로 가져옵니다.
     *
     * @param category json 으로 변환할 카테고리
     * @return json format 으로 변환된 문자열
     */
    public String getJsonText(Category category) {
        return getJSONObject(category).toString(2);
    }

    /**
     * 전달된 카테고리들을 포함한 하위 카테고리 관계를 json format 으로 가져옵니다.
     *
     * @param categories json 으로 변환할 카테고리들
     * @return json format 으로 변환된 문자열
     */
    public String getJsonText(List<Category> categories) {
        return getJSONArray(categories).toString(2);
    }

    private JSONObject getJSONObject(Category category) {
        List<Category> childList = findAllChildList(category);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", category.getId());
        jsonObject.put("name", category.getName());
        if (childList.size() != 0) {
            jsonObject.put("child_list", getJSONArray(childList));
        }
        return jsonObject;
    }

    private JSONArray getJSONArray(List<Category> categories) {
        JSONArray jsonArray = new JSONArray();
        categories.forEach(it -> jsonArray.put(getJSONObject(it)));
        return jsonArray;
    }

    private List<Category> findAllChildList(Category category) {
        List<Category> childList = new ArrayList<>();
        List<CategoryRelation> relationList = relationMap.get(category.getId());
        if (relationList != null && relationList.size() != 0) {
            relationList.forEach(it -> childList.add(getById(it.getChild_id())));
        }
        return childList;
    }

    private void save(Category category) {
        indexMap.put(category.getId(), category);

        List<Category> categoryList = nameMap.getOrDefault(category.getName(), new ArrayList<>());
        categoryList.add(category);
        nameMap.put(category.getName(), categoryList);
    }

    public void createSample() {
        Category male = create("남자");
        Category female = create("여자");

        Category exo = create(male, "엑소");
        Category bts = create(male, "방탄소년단");
        Category blackPink = create(female, "블랙핑크");

        create(exo, "공지사항");
        create(exo, "첸");
        create(exo, "백현");
        create(exo, "시우민");

        create(bts, "공지사항");
        Category anonymous = create(bts, "익명게시판");
        create(bts, "뷔");

        create(blackPink, "공지사항");
        addRelation(blackPink, anonymous);
        create(blackPink, "로제");
    }

    public void createCustom() {
        Category unique = create("유니크");

        // level 1
        Category category1 = create("카테고리 1");
        Category category2 = create("카테고리 2");

        // level 2
        Category category1_1 = create(category1, "카테고리 1-1");
        create(category1, "카테고리 1-2");
        create(category1, "카테고리 1-3");

        create(category2, "카테고리 2-1");

        addRelation(unique, category1_1);
        addRelation(unique, category2);
    }

}
