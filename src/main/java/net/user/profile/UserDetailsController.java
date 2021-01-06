package net.user.profile;
import java.io.File;
import java.io.FileOutputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.user.thumbnail.ThumbnailEntity;
import net.user.thumbnail.ThumbnailService;

@Controller
public class UserDetailsController {

	@Autowired
	ThumbnailService thumbnailService;

	@RequestMapping("/user/profile/details/{id}")
	@Transactional
	public ModelAndView details(
			@PathVariable long id,
			ModelAndView mav) {

		final ThumbnailEntity thumbnailEntity = thumbnailService.getOne(id);
		final byte[] image = thumbnailEntity.getImage();
		final File file = new File("thumbnail.jpg");

		System.out.println("");
		try(FileOutputStream out = new FileOutputStream(file)) {

             out.write(image);

		}  catch (Exception e) {

			System.out.println("Exception is:- " + e);
		}

		mav.addObject("image", file);
		mav.setViewName("/user/profile/details");

		return mav;
	}
}