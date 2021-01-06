package net.user.thumbnail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThumbnailServiceImpl implements ThumbnailService {

	@Autowired
	ThumbnailRepository repository;

	@Override
	public void save(ThumbnailEntity entity) {
		repository.save(entity);
	}

	@Override
	public void saveAndFlash(ThumbnailEntity entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public ThumbnailEntity getOne(long id) {
		return repository.getOne(id);
	}

	public static byte[] getImageFileInAPP(String fileName) {

		return new byte[1024];
	}


}