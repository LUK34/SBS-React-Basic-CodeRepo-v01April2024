package kw.kng.service;

import java.util.List;

import kw.kng.entity.BasicHelloWorld;

public interface HelloWorldService 
{
	public List<BasicHelloWorld> getHelloWorld(int pageNumber, int pageSize);
	BasicHelloWorld saveBasicHelloWorld(BasicHelloWorld bhw);
	BasicHelloWorld getBasicHelloWorldRecordById(Long id);
	void deleteBasicHelloWorldById(Long id);
	BasicHelloWorld updateBasicHelloWorldRecordById(Long id, BasicHelloWorld bwh);
	List<BasicHelloWorld> getBasicHelloWorldByName(String name);
	List<BasicHelloWorld> getBasicHelloWorldByNameAndLocation(String name, String location);
	List<BasicHelloWorld> getBasicHelloWorldByNameKeyword(String keyword);
	List<BasicHelloWorld> getBasicHelloWorldByNameOrLocation(String name, String location);
	Integer deleteBasicHelloWorldByName(String name);
}
