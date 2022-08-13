import bean.MathCalculator;
import bean.Student;
import bean.Teacher;
import config.BeanConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class App {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Student student1 = xmlApplicationContext.getBean(Student.class);
        log.info("Student name:{} age:{}", student1.getName(), student1.getAge());

        AnnotationConfigApplicationContext annotationApplicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        MathCalculator mathCalculator = annotationApplicationContext.getBean(MathCalculator.class);
        log.info("mathCalculator result:{}", mathCalculator.div(10, 0));

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        ClassPathResource res = new ClassPathResource("beans.xml");
        xmlBeanDefinitionReader.loadBeanDefinitions(res);
        Student student2 = defaultListableBeanFactory.getBean(Student.class);
        log.info("Student name:{} age:{}", student2.getName(), student2.getAge());


        GenericApplicationContext genericApplicationContext = new GenericApplicationContext(defaultListableBeanFactory);
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(genericApplicationContext);
        annotatedBeanDefinitionReader.register(BeanConfig.class);
        genericApplicationContext.refresh();
        Teacher teacher2 = genericApplicationContext.getBean(Teacher.class);
        log.info("Teacher name:{} course:{}", teacher2.getName(), teacher2.getCourse());


    }


}
