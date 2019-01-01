//Made By Archit Puri on 1/1/2019

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.persistence.MapKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class ComplaintController {
	
private static String UPLOADED_FOLDER = "E://colio//imgData//";
@Autowired
private ComplaintRepository complaintRepository;
  
  @PostMapping(path="/api/test/imgfile")
    public @ResponseBody GeneralResponse addFileTest(@RequestParam("attachedFile") MultipartFile attachedFile,
      RedirectAttributes redirectAttributes) {
    String name="Archit";

    if (attachedFile.isEmpty()) {
              redirectAttributes.addFlashAttribute("message", "Please select a attachedFile to upload");
              return new GeneralResponse(401,"No attachedFile Received");
          }

          try {

              byte[] bytes = attachedFile.getBytes();
              Path path = Paths.get(UPLOADED_FOLDER + attachedFile.getOriginalFilename());
              Files.write(path, bytes);
              redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + attachedFile.getOriginalFilename() + "'");

          } catch (IOException e) {
              e.printStackTrace();
          }
          String associatedImageUrl=UPLOADED_FOLDER + attachedFile.getOriginalFilename();
        return new GeneralResponse(200,"Complaint Successfully Added"+associatedImageUrl+"Yo");
      }
}
