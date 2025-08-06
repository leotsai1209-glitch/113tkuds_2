import java.util.*;

public class RecursiveTreePreview {
    public static void main(String[] args) {
        // 1. 模擬資料夾
        Folder root = new Folder("root");
        root.files.addAll(Arrays.asList("a.txt", "b.txt"));
        Folder sub1 = new Folder("sub1");
        sub1.files.add("c.txt");
        Folder sub2 = new Folder("sub2");
        sub2.files.addAll(Arrays.asList("d.txt", "e.txt"));
        sub1.subFolders.add(sub2);
        root.subFolders.add(sub1);

        System.out.println("=== 資料夾總檔案數 ===");
        System.out.println(countFiles(root));

        // 2. 多層選單
        MenuItem menu = new MenuItem("主選單");
        MenuItem subMenu1 = new MenuItem("選單 1");
        subMenu1.children.add(new MenuItem("子選單 1-1"));
        subMenu1.children.add(new MenuItem("子選單 1-2"));
        MenuItem subMenu2 = new MenuItem("選單 2");
        menu.children.add(subMenu1);
        menu.children.add(subMenu2);

        System.out.println("\n=== 列印多層選單 ===");
        printMenu(menu, 0);

        // 3. 巢狀陣列展平
        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, 3, Arrays.asList(4, 5)), 6);
        System.out.println("\n=== 展平巢狀陣列 ===");
        System.out.println(flattenList(nestedList));

        // 4. 巢狀清單最大深度
        System.out.println("\n=== 最大深度 ===");
        System.out.println(maxDepth(nestedList));
    }

    // ===== 1. 遞迴計算資料夾檔案數 =====
    static class Folder {
        String name;
        List<String> files = new ArrayList<>();
        List<Folder> subFolders = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }
    }

    public static int countFiles(Folder folder) {
        int count = folder.files.size();
        for (Folder sub : folder.subFolders) {
            count += countFiles(sub);
        }
        return count;
    }

    // ===== 2. 遞迴列印多層選單 =====
    static class MenuItem {
        String name;
        List<MenuItem> children = new ArrayList<>();

        MenuItem(String name) {
            this.name = name;
        }
    }

    public static void printMenu(MenuItem menu, int level) {
        System.out.println("  ".repeat(level) + "- " + menu.name);
        for (MenuItem child : menu.children) {
            printMenu(child, level + 1);
        }
    }

    // ===== 3. 遞迴展平巢狀陣列 =====
    public static List<Object> flattenList(List<?> nestedList) {
        List<Object> flatList = new ArrayList<>();
        for (Object item : nestedList) {
            if (item instanceof List<?>) {
                flatList.addAll(flattenList((List<?>) item));
            } else {
                flatList.add(item);
            }
        }
        return flatList;
    }

    // ===== 4. 遞迴計算最大深度 =====
    public static int maxDepth(List<?> nestedList) {
        int depth = 1;
        for (Object item : nestedList) {
            if (item instanceof List<?>) {
                depth = Math.max(depth, 1 + maxDepth((List<?>) item));
            }
        }
        return depth;
    }
}