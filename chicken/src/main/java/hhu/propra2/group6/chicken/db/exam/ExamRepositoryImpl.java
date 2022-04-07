package hhu.propra2.group6.chicken.db.exam;

import hhu.propra2.group6.chicken.application.repository.ExamRepository;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ExamRepositoryImpl implements ExamRepository {
    DBExamRepository dbExamRepository;

    public ExamRepositoryImpl(DBExamRepository dbExamRepository) {
        this.dbExamRepository = dbExamRepository;
    }

    @Override
    public void save(Exam exam) {
        dbExamRepository.save(toEntity(exam));
    }

    @Override
    public void delete(Exam exam) {
        dbExamRepository.deleteByInfo(exam.getName(),
                exam.getTimePeriod().getL1(),
                exam.getTimePeriod().getL2(),
                exam.getStudentid());
    }

    @Override
    public void clear() {
        dbExamRepository.deleteAll();
    }

    @Override
    public Exam findById(Long id) {
        return toExam(Objects.requireNonNull(dbExamRepository.findById(id).orElse(null)));
    }


    @Override
    public Exam findByStudentID(Long studentid) {
        return toExam(dbExamRepository.findByStudentid(studentid));
    }

    @Override
    public List<Exam> findAllByStudentID(Long studentid) {
        return dbExamRepository.findAllByStudentid(studentid).stream().map(this::toExam).collect(Collectors.toList());
    }

    public Exam toExam(ExamDTO examDTO) {
        return new Exam(examDTO.getId(), examDTO.getName(), new TimePeriod(examDTO.getBegin(), examDTO.getEnd()), examDTO.isOnline(), examDTO.getStudentid());
    }

    public ExamDTO toEntity(Exam exam) {
        return ExamDTO.create(exam.getName(), exam.getTimePeriod().getL1(), exam.getTimePeriod().getL2(), exam.isOnline(), exam.getStudentid());
    }

}
