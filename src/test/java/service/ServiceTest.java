package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.*;

public class ServiceTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);


    @Test
    public void saveStudent1() {
        int result = service.saveStudent("1", "Bob", 500);
        assert (result == 0);
    }

    @Test
    public void saveStudent2() {
        int result = service.saveStudent("1", "Bob", -1);
        assert (result == 1);
    }

    @Test
    public void saveStudent3() {
        int result = service.saveStudent("", "Bob", 500);
        assert (result == 1);
    }

    @Test
    public void saveStudent4() {
        int result = service.saveStudent("1", "", 500);
        assert (result == 1);
    }

    @Test
    public void saveStudent5() {
        int result = service.saveStudent("1", "Bob", 500);
        assert (result == 0);
    }

    @Test
    public void addAssignment_InvalidID() {
        int result = service.saveTema("", "descriere", 1, 1);
        assert( result == 1 );
    }

    @Test
    public void addAssignment_InvalidDescription() {
        int result = service.saveTema("id", "", 1, 1);
        assert( result == 1 );
    }

    @Test
    public void addAssignment_InvalidLowerBoundDeadline() {
        int result = service.saveTema("id", "descriere", -1, 1);
        assert( result == 1 );
    }

    @Test
    public void addAssignment_InvalidUpperBoundDeadline(){
        int result = service.saveTema("id","descriere",15,1);
        assert( result == 1 );
    }

    @Test
    public void addAssignment_InvalidDealineLowerThanStartline(){
        int result = service.saveTema("id", "descriere",4,5);
        assert( result == 1);
    }

    @Test
    public void addAssignment_InvalidLowerBoundStartline(){
        int result = service.saveTema("id", "descriere", 1,-1);
        assert( result == 1);
    }

    @Test
    public void addAssignment_InvalidUpperBoundStartline(){
        int result = service.saveTema("id","descriere",15,15);
        assert( result == 1);
    }
}
