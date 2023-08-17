import java.util.ArrayList;
import java.util.Scanner;

public class __main__ {
    public static void stuManage() {
        ArrayList<Student> stuList = new ArrayList<>();
        while (true) {
            System.out.println("""
                    -----------欢迎来到学生管理系统-----------
                    "1:添加学生"
                    "2:删除学生"
                    "3:修改学生"
                    "4:查询学生"
                    "5:退出"
                    "请输入你的选择:\"""");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> {
                    stuList.add(addStudent(stuList));
                    System.out.println("添加成功！");
                    pause();
                }
                case "2" -> {
                    System.out.println("请输入你要删除学生的id");
                    int num2 = checkId(stuList);
                    if (num2 == -1)
                        System.out.println("要删除的学生不存在，即将返回初始菜单!");
                    else {
                        stuList.remove(num2);
                        System.out.println("删除成功！");
                    }
                    pause();
                }
                case "3" -> {
                    System.out.println("请输入你要修改学生的id");
                    int num3 = checkId(stuList);
                    if (num3 == -1)
                        System.out.println("要修改的学生不存在，即将返回初始菜单!");
                    else {
                        System.out.println("请输入你要修改学生的姓名");
                        String name = sc.next();
                        stuList.get(num3).setName(name);
                        System.out.println("请输入你要修改学生的年龄");
                        int age = sc.nextInt();
                        stuList.get(num3).setAge(age);
                        System.out.println("请输入你要修改学生的家庭住址");
                        String address = sc.next();
                        stuList.get(num3).setAddress(address);
                        System.out.println("修改成功！");
                    }
                    pause();
                }
                case "4" -> {
                    if (stuList.isEmpty())
                        System.out.println("当前无学生信息，请添加后在查询");
                    else {
                        System.out.println("id\t\t姓名\t\t年龄\t\t家庭住址");
                        for (Student stu : stuList) {
                            System.out.println(stu.getId() + "\t\t" + stu.getName() + "\t\t" + stu.getAge() + "\t\t" + stu.getAddress());
                        }
                    }
                    pause();
                }
                case "5" -> {
                    System.out.println("即将退出...");
                    System.exit(0);
                    pause();
                }
                default -> {
                    System.out.println("输入有误,请重新输入!");
                    pause();
                }
            }
        }
    }

    public static Student addStudent(ArrayList<Student> list) {
        Student stu = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要添加学生的id");
        String id = sc.next();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (id.equals(list.get(i).getId())) {
                    System.out.println("id已存在！请重新输入！");
                    System.out.println("请输入你要添加学生的id");
                    id = sc.next();
                    i--;
                }
            }
        }
        System.out.println("请输入你要添加学生的姓名");
        String name = sc.next();
        System.out.println("请输入你要添加学生的年龄");
        int age = sc.nextInt();
        System.out.println("请输入你要添加学生的家庭住址");
        String address = sc.next();
        stu.setId(id);
        stu.setName(name);
        stu.setAge(age);
        stu.setAddress(address);
        return stu;
    }

    public static int checkId(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public static void pause(){
        System.out.println("请按回车键返回初始菜单");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
    }
}
