import java.lang.reflect.*;
import java.util.Scanner;

/**
 * @author Adrian Kaminski
 * Simple programm to display all fields, constructors and methods of any valid class with their modifiers, parameters and return types.
 * Either input the class name in the first parameter of args[] or leave it empty and input it via console.
 */
public class Main {
    public static void main(String[] args) {
	    String name = "";
        if(args.length == 0){
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter any class.");
            name =  scn.nextLine();
        } else {
            name = args[0];
        }
        String output = "";
        try {
            Class cls = Class.forName(name);

            // Get all superclasses
            output += "Superclasses: " + cls.getName();
            Class tmp = cls.getSuperclass();
            while(tmp != null){
                output += " <-- " + tmp.getName();
                tmp = tmp.getSuperclass();
            }

            output += "\nclass " + cls.getName() + " {\n";

            // Get all fields
            Field[] fields = cls.getFields();
            for (Field field:fields) {
                output += "\t" + Modifier.toString(field.getModifiers()) + " "
                        + field.getName()
                        + ";\n";
            }
            output += "\n";

            // Get all constructors
            Constructor[] constructors = cls.getConstructors();
            for (Constructor constructor:constructors) {
                output += "\t" + constructor + ";\n";
            }
            output += "\n";

            // Get all methods
            Method[] methods = cls.getDeclaredMethods();
            for (Method method:methods) {
                output += "\t" + Modifier.toString(method.getModifiers()) + " "
                        + method.getReturnType() + " "
                        + method.getName() + " ";
                Class[] params = method.getParameterTypes();
                output += "(";
                for (int i = 0; i < method.getParameterCount(); i++) {
                    output += params[i];
                    if(i + 1 < method.getParameterCount()){
                        output += ", ";
                    }
                }
                output += ");\n";
            }
            output += "}";
        } catch(ClassNotFoundException e) {
            System.out.println("Not a valid class. Restart.");
        }
        System.out.print(output);
    }
}
