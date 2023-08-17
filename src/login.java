
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class login {
    public static void main(String[] args) {
        ArrayList<User> userList = new ArrayList<>();
        while(true){
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose){
                case "1" ->{
                    if(loginUser(userList)){
                        __main__.stuManage();
                    }
                    else{
                        pause();
                    }
                }
                case "2" ->{
                    userList.add(enroll(userList));
                    System.out.println("注册成功...");
                    System.out.println(userList);
                    pause();
                }
                case "3" ->{
                    forgetPassword(userList);
                    System.out.println(userList);
                    pause();
                }
                default ->
                    System.exit(0);
            }
        }
    }

    public static User enroll(ArrayList<User> userList){
        User us = new User();
        System.out.println("请输入用户名:");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        for (User user : userList){
            if(username.equals(user.getUsername())){
                System.out.println("用户名已存在！即将返回主界面！");
                return null;
            }
        }
        while(true){
            if(username.length() <3 || username.length() >15){
                System.out.println("用户名过长或者过短！请重新输入用户名！");
                System.out.println("请输入用户名:");
                username = sc.next();
            }
            else
                break;
        }
        while(true){
            boolean enFlag = false;
            boolean numFlag = false;
            char[] tempUn = username.toCharArray();
            for(char i : tempUn){
                if(i >= 'a' && i <='z')
                    enFlag = true;
                if(i >= '0' && i <= '9')
                    numFlag = true;
            }
            if(!enFlag || !numFlag){
                System.out.println("请使用字母加数字的组合！请重新输入用户名！");
                System.out.println("请输入用户名:");
                username = sc.next();
            }
            else
                break;
        }
        us.setUsername(username);



        String password;
        while(true){
            System.out.println("请输入密码:");
            String firstPw = sc.next();
            System.out.println("请再次输入和刚才相同的密码:");
            String secondPw = sc.next();
            if(firstPw.equals(secondPw)) {
                password = secondPw;
                break;
            }
            else
                System.out.println("两次密码不一致，请重新输入！");
        }
        us.setPassword(password);



        System.out.println("请输入你的身份证号码:");
        String id = sc.next();
        while(true){
            if(id.length() == 18)
                break;
            else{
                System.out.println("身份证号码的长度必须18位！请重新输入身份证号码！");
                System.out.println("请输入身份证号码:");
                id = sc.next();
            }
        }
        while(true){
            if(id.charAt(0) == '0'){
                System.out.println("身份证号码不能以0为开头！请重新输入身份证号码！");
                System.out.println("请输入身份证号码:");
                id = sc.next();
            }
            else
                break;
        }
        while(true){
            int idError = 0;
            char[] tempId17 = id.substring(0,16).toCharArray();
            for(char i : tempId17){
                if(i < '0' || i > '9')
                    idError++;
            }
//            System.out.println("idError:"+idError);
            char tempId18 =id.charAt(17);
            if(!((tempId18 >= '0' && tempId18 <= '9') || tempId18 == 'x' || tempId18 == 'X')){
                idError++;
            }
            if(idError > 0) {
                System.out.println("身份证号码不符合要求！请重新输入身份证号码！");
                System.out.println("请输入身份证号码:");
                id = sc.next();
            }
            else
                break;
        }
        us.setId(id);



        System.out.println("请输入你的手机号码:");
        String phone = sc.next();
        while(true){
            if(phone.length() == 11)
                break;
            else{
                System.out.println("手机号码的长度必须11位！请重新输入手机号码！");
                System.out.println("请输入手机号码:");
                phone = sc.next();
            }
        }
        while(true){
            int phoneError = 0;
            char[] tempPhone = phone.toCharArray();
            for (char i : tempPhone){
                if(i < '0' || i > '9')
                    phoneError++;
            }
            if(phone.charAt(0) == '0')
                phoneError++;
            if(phoneError > 0) {
                System.out.println("手机号码不符合要求！请重新输入手机号码！");
                System.out.println("请输入手机号码:");
                phone = sc.next();
            }
            else
                break;
        }
        us.setPhone(phone);
        return us;
    }

    public static boolean loginUser(ArrayList<User> userList){
        for (int i = 0; i < 3; i++) {
            if(i != 0){
                System.out.println("你还有"+(3-i)+"次机会");
            }
            System.out.println("请输入用户名:");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            int unIndex;
            if(userList.isEmpty()){
                System.out.println("用户名未注册，请先注册");
                return false;
            }
            for (unIndex = 0; unIndex < userList.size(); unIndex++) {
                if(username.equals(userList.get(unIndex).getUsername()))
                    break;
                if(unIndex == userList.size()-1){
                    System.out.println("用户名未注册，请先注册");
                    return false;
                }
            }
            System.out.println("请输入密码:");
            String password = sc.next();
            while(true){
                String captchaOut = captcha();
                System.out.println("请输入验证码:");
                System.out.println(captchaOut);
                String captchaIn = sc.next();
                if(captchaIn.equals(captchaOut)){
                    break;
                }
                else{
                    System.out.println("验证码有误！请重新输入！");
                }
            }
            if(password.equals(userList.get(unIndex).getPassword())){
                return true;
            }
            else{
                System.out.println("密码有误！请重新输入！");
            }
        }
        System.out.println("登录尝试已经达到3次！即将返回主菜单...");
        return false;
    }

    public static void forgetPassword(ArrayList<User> userList){
        System.out.println("请输入用户名：");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        int unIndex;
        if(userList.isEmpty()){
            System.out.println("未注册");
            return ;
        }
        for (unIndex = 0; unIndex < userList.size(); unIndex++) {
            if(username.equals(userList.get(unIndex).getUsername()))
                break;
            if(unIndex == userList.size()-1){
                System.out.println("未注册");
                return ;
            }
        }
        System.out.println("请输入身份证号码:");
        String id = sc.next();
        System.out.println("请输入手机号码:");
        String phone = sc.next();
        if(id.equals(userList.get(unIndex).getId()) && phone.equals(userList.get(unIndex).getPhone())){
            System.out.println("账号信息匹配，请输入密码进行修改");
            String password = sc.next();
            userList.get(unIndex).setPassword(password);
        }
        else{
            System.out.println("账号信息不匹配，修改失败");
        }
    }

    public static String captcha(){
        ArrayList<Character> characters = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < 26; i++) {
            characters.add((char) ('a'+i));
            characters.add((char) ('A'+i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = rd.nextInt(characters.size());
            char c = characters.get(index);
            sb.append(c);
        }
        char num = (char) ('0'+rd.nextInt(10));
        sb.insert(rd.nextInt(5),num);
        return sb.toString();
    }

    public static void pause(){
        System.out.println("请按回车键返回主菜单...");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
    }

}
