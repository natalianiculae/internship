package com.natalia.internship.restcontroller;

import com.natalia.internship.NotFoundException;
import com.natalia.internship.model.Subject;
import com.natalia.internship.persistence.SubjectTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectRestController {

	@Autowired
	private SubjectTemplate subjectTemplate;

	@GetMapping("/subjects")
	public List<Subject> getSubjects() {
		return subjectTemplate.getSubjects();
	}

	@PostMapping("/subjects")
	public void saveSubjects(@RequestBody Subject subject) {
		subjectTemplate.saveSubject(subject.getSubject());
	}

	@GetMapping("/subjects/{subjectId}")
	public Subject getSubject(@PathVariable int subjectId) {
		Subject subject = subjectTemplate.getSubject(subjectId);
		if (subject == null) {
			throw new NotFoundException("Subject with id " + subjectId + " not found");
		}
		return subject;
	}

	@DeleteMapping("/subjects/{subjectId}")
	public void deleteSubject(@PathVariable int subjectId) {
		subjectTemplate.deleteSubject(subjectId);
	}

	@PutMapping("/subjects/{subjectId}")
	public void updateSubject(@PathVariable int subjectId, @RequestBody Subject subject) {
		subjectTemplate.updateSubject(subjectId, subject.getSubject());
	}

}
