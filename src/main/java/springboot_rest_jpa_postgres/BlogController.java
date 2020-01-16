package springboot_rest_jpa_postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import java.util.List;
import java.util.Map;

@RestController
public class BlogController {
    @Autowired
    BlogRespository blogRespository;

    //@GetMapping(path="/blog", produces = { "application/json", "application/xml" })         //or, @RequestMapping(value = "/blog", method = RequestMethod.GET)
    @GetMapping(path="/blog")
    public List<Blog> index(){
        return blogRespository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return blogRespository.findById(blogId).get();
        // return blogRespository.findOne(blogId);
    }

    @GetMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogRespository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String content = body.get("content");
        return blogRespository.save(new Blog(title, content));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body){
        int blogId = Integer.parseInt(id);
        //Blog blog = blogRespository.findOne(blogId);
        Blog blog = blogRespository.findById(blogId).get();
        blog.setTitle(body.get("title"));
        blog.setContent(body.get("content"));
        return blogRespository.save(blog);
    }

    //@RequestMapping(value = "/blog/{id}", method = RequestMethod.POST)
    @DeleteMapping("/blog/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        //blogRespository.delete(blogId);
        blogRespository.deleteById(blogId);
        return true;
    }
    //-------------------------------------------------------------------
    @Autowired
    ArtemisProducer artemisProducer;

    @RequestMapping(value="/send")
    public String produce(@RequestParam("msg")String msg){
        artemisProducer.send(msg);
        return "Message send successfully";
    }

    @Autowired
    ArtemisConsumer artemisConsumer;

    @RequestMapping(value = "/receive")
    public void receive(String msg) throws JMSException {
        artemisConsumer.receive(msg);
    }
}
