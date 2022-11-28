package mentoss.menmeet.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
public class MetaverseController {

	private final ResourceLoader resourceLoader;
	private final HttpServletResponse response;
	@GetMapping("/downloadMetaverseClient")
	public void downloadClient() throws Exception {
		String path = "/home/ubuntu/MenMeetClient.exe";

		File file = new File(path);
		response.setHeader("Content-Disposition","attachment;filename="+file.getName());
		FileInputStream fileInputStream = new FileInputStream(path);
		OutputStream out = response.getOutputStream();

		int read =0;
		byte[]buffer = new byte[1024];
		while((read = fileInputStream.read(buffer))!=-1){
			out.write(buffer,0,read);
		}
	}
}
