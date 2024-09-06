package com.example.postcurd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@RestController
//@RequestMapping("/Students")
public class Controller {

	@Autowired
	StudentRes rep;
	
	@GetMapping("/Students")
	public List<Student> getAllStudents()
	{
		return rep.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudents(@PathVariable int id)
	{
		return rep.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping("/add")
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createStudent(@RequestBody Student st)
	{
		rep.save(st);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> update(@PathVariable int id,@RequestBody Student s)
	{
		return rep.findById(id).map(existingStudent ->{
			existingStudent.setName(s.getName());
			existingStudent.setCourse(s.getCourse());
			existingStudent.setAge(s.getAge());
			
			Student std=rep.save(existingStudent);
			return ResponseEntity.ok(std);
         })
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Student> delete(@PathVariable int id) 
	{
	    if (!rep.existsById(id))
	    {
	        return ResponseEntity.notFound().build();
	    }
	    rep.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{no}/{size}")
	public List<Student> pagination(@PathVariable int no,@PathVariable int size)
	{
		Pageable p=PageRequest.of(no, size);
		Page<Student> pg=rep.findAll(p);
		return pg.hasContent()? pg.getContent():List.of();
		
	}

	@GetMapping("/sort")
	public List<Student> sorting(@RequestParam String f,@RequestParam(defaultValue="asc") String st)
	{
		Sort.Direction sd=st.equalsIgnoreCase("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
		
		return rep.findAll(Sort.by(sd,f));
		
	}
	
	@GetMapping("/{no}/{size}/sort")
	public List<Student> pagi_sort(@PathVariable int no,@PathVariable int size,@RequestParam String f,@RequestParam(defaultValue="asc") String st)
	{
		Sort so=st.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(f).ascending():Sort.by(f).descending();
		Pageable  pg= PageRequest.of(no, size,so);
		Page<Student> pr=rep.findAll(pg);
		return pr.hasContent()?pr.getContent():List.of();
		
	}
}
