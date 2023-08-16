# 学生管理系统
## 需求：
采取控制台的方式去制作学生管理系统
## 分析:
### 初始菜单:
    -----------欢迎来到学生管理系统-----------
    "1:添加学生"
    "2:删除学生"
    "3:修改学生"
    "4:查询学生"
    "5:退出"
    "请输入你的选择:"
### 学生类:
属性:id，姓名，年龄，家庭住址
### 添加功能：
键盘录入每一个学生信息并添加，需要满足以下要求:
+ id唯一
### 删除功能:
键盘录入要删除的学生id，需要满足以下需求:
+ id存在删除
+ id不存在，需要提示不存在，并且回到初始菜单
### 修改功能:
+ id存在,继续录入其他信息
+ id不存在，需要提示不存在，并且回到初始菜单
### 查询功能：
+ 如果没有学生信息，提示：当前无学生信息，请添加后在查询
+ 如果有学生信息，需要按照以下格式输出。

      id      姓名      年龄      家庭住址
      001     张三      23        南京
      002     李四      24        北京
      003     王五      25        广州
      004     赵六      24        深圳
