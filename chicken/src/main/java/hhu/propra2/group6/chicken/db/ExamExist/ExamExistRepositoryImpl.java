package hhu.propra2.group6.chicken.db.ExamExist;

import hhu.propra2.group6.chicken.application.repository.ExamExistRepository;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.timeperiod.TimePeriod;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExamExistRepositoryImpl implements ExamExistRepository {
    DBExamExistRepository dbExamExistRepository;

    public ExamExistRepositoryImpl(DBExamExistRepository dbExamExistRepository) {
        this.dbExamExistRepository = dbExamExistRepository;
    }

    @Override
    public void save(Exam exam) {
        if (!isExist(exam.getLsfid())) {
            dbExamExistRepository.save(toEntity(exam));
        }
    }

    @Override
    public Boolean isExist(Long lsfid) {
        return dbExamExistRepository.existsByLsfid(lsfid);
    }

    @Override
    public Exam findByLsfid(Long lsfid) {
        return toExam(dbExamExistRepository.findByLsfid(lsfid));
    }

    @Override
    public List<Exam> findAll() {
        return dbExamExistRepository.findAll().stream().map(this::toExam).collect(Collectors.toList());
    }

    @Override
    public void deleteByLsfid(Long lsfid) {
        dbExamExistRepository.deleteByLsfid(lsfid);
    }


    public Exam toExam(ExamExistDTO examExistDTO) {
        Exam exam = new Exam(examExistDTO.getId(), examExistDTO.getName(), new TimePeriod(examExistDTO.getBegin(), examExistDTO.getEnd()), examExistDTO.isOnline(), null);
        exam.setLsfid(examExistDTO.getLsfid());
        return exam;
    }

    public ExamExistDTO toEntity(Exam exam) {
        return ExamExistDTO.create(exam.getName(), exam.getTimePeriod().getL1(), exam.getTimePeriod().getL2(), exam.isOnline(), exam.getLsfid());
    }
}
