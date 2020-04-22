package rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.gridfs.model.GridFSFile;

import service.UserDto;
import service.UserService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/User")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private GridFsTemplate gridFsTemplate;

	@GetMapping("/all")
	public List<UserDto> getAll() {
		return userService.getAll();
	}
	
	@GetMapping("/all/clients")
	public List<UserDto> getAllClients() {
		return userService.getAllClients();
	}

	@GetMapping("/{id}")
	public UserDto get(@PathVariable String id) {
		return userService.getUser(id);
	}

	@PostMapping("/create")
	public UserDto createUser(@RequestBody UserDto UserDto) {
		return userService.createUser(UserDto);
	}

	@PutMapping("/update/{UserId}")
	public UserDto updateUser(@RequestBody UserDto UserDto, @PathVariable String UserId) {

		return userService.updateUser(UserDto, UserId);

	}

	@DeleteMapping("/all")
	public void deleteAll() {
		userService.deleteAll();
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}
	
	@GetMapping(value = "/getPicture/{id}")
	public void getPictureURL(HttpServletResponse response, @PathVariable String id) throws IOException {

      
	  GridFSFile fsFile = this.userService.getPicture(id);
      
      // display name from file.

      String fileName = fsFile.getFilename();
  
      //set Content Type
      response.setContentType(URLConnection.guessContentTypeFromName(fileName));
      
	  // the length
      response.setContentLengthLong(fsFile.getLength());

      fileName = URLDecoder.decode(fileName, "ISO8859_1");

      response.setHeader("Content-disposition", "inline; filename=" + fileName);

      // The inputstream from mongo

      GridFsResource resource = gridFsTemplate.getResource(fsFile.getFilename());
      InputStream inputStream = resource.getInputStream();

      // response out put stream will be used to write the file content.

      OutputStream out = response.getOutputStream();

      byte[] buf = new byte[1024];

      int count = 0;

      while ((count = inputStream.read(buf)) >= 0) {

             out.write(buf, 0, count);

      }

      out.close();

      inputStream.close();

}	
}
