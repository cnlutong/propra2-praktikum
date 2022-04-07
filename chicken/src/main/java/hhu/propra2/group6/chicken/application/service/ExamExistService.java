package hhu.propra2.group6.chicken.application.service;

import hhu.propra2.group6.chicken.application.repository.ExamExistRepository;
import hhu.propra2.group6.chicken.domain.exam.Exam;
import hhu.propra2.group6.chicken.domain.time.CurrentTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
public class ExamExistService {

    private final ExamExistRepository examExistRepository;

    public ExamExistService(ExamExistRepository examExistRepository) {
        this.examExistRepository = examExistRepository;
    }

    public void save(Exam exam) {
        examExistRepository.save(exam);
    }

    public Boolean isExist(Long lsfid) {
        return examExistRepository.isExist(lsfid);
    }

    public Exam findByLsfid(Long lsfid) {
        return examExistRepository.findByLsfid(lsfid);
    }

    public void deleteByLsfid(Long lsfid) {
        examExistRepository.deleteByLsfid(lsfid);
    }

    public List<Exam> findAll() {
        return examExistRepository.findAll();
    }

    public List<Exam> findAllCurrent(CurrentTime currentTime) {
        return findAll().stream().filter(exam -> exam.getTimePeriod().getL1().isAfter(currentTime.now())).toList();
    }

    public Boolean getEventNameByLsfId(String lsfId, Function<Long, Optional<Document>> getDocumentById) throws IOException {
        String url = "https://lsf.hhu.de/qisserver/rds?state=verpublish&status=init&vmfile=no&publishid=" +
                lsfId +
                "&moduleCall=webInfo&publishConfFile=webInfo&publishSubDir=veranstaltung";

        Document doc = getDocumentById.apply(Long.valueOf(lsfId)).get();
        String content = doc.outerHtml();
        return content.contains(lsfId);
    }

    public Boolean getEventNameByLsfId(String lsfId) throws IOException {
        return getEventNameByLsfId(lsfId, (id) -> {
            try {
                return Optional.of(Jsoup.connect("https://lsf.hhu.de/qisserver/rds?state=verpublish&status=init&vmfile=no&publishid=" +
                        lsfId +
                        "&moduleCall=webInfo&publishConfFile=webInfo&publishSubDir=veranstaltung").get());
            } catch (IOException e) {
                return Optional.empty();
            }
        });
    }
}
