package bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitalizationDemo.class);
        context.refresh();
        context.close();
        Thread.sleep(5000);
        System.gc();
        Thread.sleep(5000);
    }
}
