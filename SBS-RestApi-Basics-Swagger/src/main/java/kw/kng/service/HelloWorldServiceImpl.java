package kw.kng.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kw.kng.entity.BasicHelloWorld;
import kw.kng.repository.HelloWorldRepo;

@Service
public class HelloWorldServiceImpl implements HelloWorldService
{
	/*
	private static List<BasicHelloWorld> bwhList= new ArrayList<>();
	
	static
	{
		BasicHelloWorld bwh= new BasicHelloWorld();
		bwh.setName("Hello World");
		bwh.setAge(28L);
		bwh.setDepartment("IT");
		bwh.setEmail("helloworld@gmail.com");
		bwh.setLocation("India");
		bwhList.add(bwh);
		
		 	bwh= new BasicHelloWorld();
			bwh.setName("Hello World_v1");
			bwh.setAge(29L);
			bwh.setDepartment("CSE");
			bwh.setEmail("helloworldv1@gmail.com");
			bwh.setLocation("India");
			bwhList.add(bwh);
	}
	
	*/
	
	
	@Autowired
	private HelloWorldRepo hwrepo;
	
	@Override
	public List<BasicHelloWorld> getHelloWorld(int pageNumber, int pageSize)//Pagination and sorting logic applied here
	{
		Pageable pages= PageRequest.of(pageNumber, pageSize, Direction.DESC,  "id" , "name"); 
		return hwrepo.findAll(pages).getContent();
	}
	
	@Override
	public BasicHelloWorld saveBasicHelloWorld(BasicHelloWorld bhw)
	{
		return hwrepo.save(bhw);
	}
	
	@Override
	public BasicHelloWorld getBasicHelloWorldRecordById(Long id)
	{
		Optional<BasicHelloWorld> bwh= hwrepo.findById(id);
		if(bwh.isPresent())
		{
			return bwh.get();
		}
		throw new RuntimeException("BasicHelloWorld record for this specific is not found. Id Value ===  " + id);
	}
	
	@Override
	public void deleteBasicHelloWorldById(Long id)
	{
		BasicHelloWorld bwh=getBasicHelloWorldRecordById(id);
		hwrepo.delete(bwh);
	}
	
	public BasicHelloWorld updateBasicHelloWorldRecordById(Long id, BasicHelloWorld bwh)
	{
		BasicHelloWorld newbwh= getBasicHelloWorldRecordById(id);
		newbwh.setName(bwh.getName() != null ? bwh.getName() : newbwh.getName());
		newbwh.setAge(bwh.getAge() != null ? bwh.getAge() : newbwh.getAge());
		newbwh.setLocation(bwh.getLocation() != null ? bwh.getLocation() : newbwh.getLocation());
		newbwh.setEmail(bwh.getEmail() != null ? bwh.getEmail() : newbwh.getEmail());
		newbwh.setDepartment(bwh.getDepartment()!= null ? bwh.getDepartment(): newbwh.getDepartment());
		return hwrepo.save(newbwh);
	}

	@Override
	public List<BasicHelloWorld> getBasicHelloWorldByName(String name) 
	{
		return hwrepo.findByName(name);
	}
	
	@Override
	public List<BasicHelloWorld> getBasicHelloWorldByNameAndLocation(String name, String location)
	{
		return hwrepo.findByNameAndLocation(name, location);
	}

	@Override
	public List<BasicHelloWorld> getBasicHelloWorldByNameKeyword(String keyword) //Sorting logic is applied here
	{
		Sort sort=Sort.by(Sort.Direction.DESC, "id");
		return  hwrepo.findByNameContainingIgnoreCase(keyword,sort);
	}

	@Override
	public List<BasicHelloWorld> getBasicHelloWorldByNameOrLocation(String name, String location) 
	{
		// TODO Auto-generated method stub
		return hwrepo.getBasicHelloWorldByNameAndLocation(name, location);
	}

	@Override
	public Integer deleteBasicHelloWorldByName(String name)
	{
		return hwrepo.deleteBasicHelloWorldByName(name);
	}
	
	
	
	
	
}
