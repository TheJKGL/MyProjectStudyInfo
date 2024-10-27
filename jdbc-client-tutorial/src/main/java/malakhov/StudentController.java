package malakhov;

import malakhov.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @GetMapping(path = "/students")
    public void getAllStudents() {
        //studentDao.getAllStudentWithFetchSize();
    }
}
