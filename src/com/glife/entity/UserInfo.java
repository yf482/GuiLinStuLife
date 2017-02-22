package com.glife.entity;
import java.io.Serializable;  
  
public class UserInfo implements Serializable{  
  
    /** 
     *  1）连接数据库,加载驱动: Class.forName(DRIVER); DRIVER = "com.mysql.jdbc.Driver";这本身就是反射！！

      (2) 利用用户名和密码及数据库的名字连接，这一步才是真正的连接:

connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); 

其中：String URL = "jdbc:mysql://localhost:3306/mydb";

     (3)编写一个sql语句，其中的参数用?来代替，然后将参数写到List里。

执行:pstmt = connection.prepareStatement(sql); 然后将参数从list里取出来填充到pstmt里。

     (4)如果是增、删、改执行:result = pstmt.executeUpdate(); 其中的result是执行完影响的数据库里的行数，也即几条记录。
     	如果是查询执行:resultSet = pstmt.executeQuery(); 返回的类型是ResultSet类型。之后就是把resultSet 
     	弄成Map或List<Map>传递出去，给查询者看。
     	3.关于查询操作，在得到resultSet后利用getMetaData得到表的结构信息，如getColumnCount()得到有多少个列。
     	String cols_name = metaData.getColumnName(i+1); 
     	得到每个列的属性名称，如是id、username还是pswd.然后从Object cols_value = resultSet.getObject(cols_name);取出来，
     	放到Map或List<Map>里。

4.关于查询里利用的反射操作，步骤如下:

     (1) T resultObject = cls.newInstance(); 利用class文件的newInstance()方法创建一个实例。

     (2)在通过getColumnCount()得到有多少个列之后，进入循环，

                 String cols_name = metaData.getColumnName(i+1);
                 Object cols_value = resultSet.getObject(cols_name);

    读取每一列的属性名字和放的值。通过属性的名字cols_name进行反射:Field field = cls.getDeclaredField(cols_name);这样就得到了Field 等于类里的成员变量，field.setAccessible(true); //打开javabean的访问权限 在利用set方法将从数据库中查出来的cols_value通过JavaBean
     也即定义的UserInfo这个类的 set方法赋进去。field.set(resultObject, cols_value);
     
     5.一般意义上，要利用Java的反射需要以下步骤

     (1)加载Class对象，这个一般有两种方式：Class cls1 = UserInfo.class  或

Class cls2 = Class.forName("domain.UserInfo") 后者是利用包名+类名的方法。

   (2)反射出来Class之后干啥事呢？一个类不外乎构造函数、成员变量、成员函数。所以得到Class之后就可以干这三件事。

     A、关于构造函数，获得Constructor 有四种方法: 

 
Constructor getConstructor(Class[] params) 

Constructor[] getConstructors() 

Constructor getDeclaredConstructor(Class[] params) 

 
Constructor[] getDeclaredConstructors()  

这四个函数，如果不传参数则是获得所有的构造函数，得到的是一个集合。如果传特定的参数，则是寻找这个特定的构造函数，不带Declared是获得公共的public，带了Declared是可以获得私有构造函数。 得到构造函数后就可以利用反射创建实例了：

 Constructor con1[] = cls1.getDeclaredConstructors();
         con1[1].setAccessible(true);
     Object obj1 = con1[1].newInstance(new Object[]{"tom"}); 如果直接调用clcs.newInstance（）则是用默认的构造函数创建实例。

      B、关于成员变量，同样有四种方法:

public Field getDeclaredField(String name)  获取任意指定名字的成员
public Field[] getDeclaredFields()          获取所有的成员变量
public Field getField(String name)          获取任意public成员变量
public Field[] getFields()                  获取所有的public成员变量

本文封装的JdbcUtils类就是利用这种方式操作类里的私有成员变量，记得要setAccessible打开开关。如下：

Field field = cls.getDeclaredField(cols_name);
field.setAccessible(true); //打开javabean的访问权限
field.set(resultObject, cols_value);

    C、关于成员函数，也有四种方法:

public Method[] getMethods()    获取所有的共有方法的集合
public Method getMethod(String name,Class<?>... parameterTypes) 获取指定公有方法 ,

参数1：方法名 参数2：参数类型集合  
public Method[] getDeclaredMethods()  获取所有的方法
public Method getDeclaredMethod(String name,Class<?>... parameterTypes) 获取任意指定方法

下面是利用文中的UserInfo这个类写的一个完成的反射例子，拿到setUsername（String username）方法，然后反射。再拿到getUsername()方法再反射，然后打印出结果:

Class clcs = UserInfo.class;
try {
Object obj = clcs.newInstance();
Method f = clcs.getDeclaredMethod("setUsername", String.class);
f.invoke(obj, "yan123");
Method f2 = clcs.getDeclaredMethod("getUsername", null);
Object name = f2.invoke(obj, null);
System.out.println("反射得到的名字 = "  +  name);


} catch (InstantiationException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IllegalAccessException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (NoSuchMethodException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (SecurityException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IllegalArgumentException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (InvocationTargetException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

在反射方法的时候，Method f = clcs.getDeclaredMethod("setUsername", String.class); 
原函数里的输入参数是什么类型，就写什么类型.class. 如原来的setXXX需要输入参数String，反射的时候就写String.class.

6. JavaBean是反射的一种，反射对构造函数之类的没任何要求，JavaBean要求这个类必须继承Serializable即可串行化，
另外构造函数必须为public. 另外，就是JavaBean在得到某个field后可以直接调用set和get，而不必再反射得到method后再执行。

    最后，反射是在程序运行的时候而非编译时！！！
     */  
    private static final long serialVersionUID = 1L;  
  
    private int id;  
    private String username;  
    private String pswd;  
    
      
    public UserInfo() {  
        // TODO Auto-generated constructor stub  
    }  
  
    public int getId() {  
        return id;  
    }  
  
    public void setId(int id) {  
        this.id = id;  
    }  
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPswd() {  
        return pswd;  
    }  
  
    public void setPswd(String pswd) {  
        this.pswd = pswd;  
    }  
  
    @Override  
    public String toString() {  
        return "UserInfo [id=" + id + ", username=" + username + ", pswd="  
                + pswd + "]";  
    }  
  
  
  
  
  
}