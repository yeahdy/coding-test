import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            int difficulty = scanner.nextInt();
            map.computeIfAbsent(difficulty, k -> new TreeSet<>()).add(id);
        }

        while (scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("add")) {
                int id = scanner.nextInt();
                int difficulty = scanner.nextInt();
                map.computeIfAbsent(difficulty, k -> new TreeSet<>()).add(id);
            } else if (command.equals("solved")) {
                int id = scanner.nextInt();
                for (TreeSet<Integer> ids : map.values()) {
                    if (ids.remove(id)) {
                        if (ids.isEmpty()) {
                            map.values().remove(ids);
                        }
                        break;
                    }
                }
            } else if (command.equals("recommend")) {
                int x = scanner.nextInt();
                if (x == 1) {
                    System.out.println(map.lastEntry().getValue().last());
                } else {
                    System.out.println(map.firstEntry().getValue().first());
                }
            }
        }
        scanner.close();
    }
}