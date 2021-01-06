package net.user.thumbnail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import net.common.constant.Constant.FolderName;

@Entity
@Data
@Table(name = "THUMBNAIL_TABLE")
public class ThumbnailEntity {

	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "IMAGE_FILE_NAME", nullable = false)
	private String imageFileName;

	@Lob
	@Column(name = "IMAGE", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] image;

	/**
	 * コンストラクタ
	 */
	public ThumbnailEntity() {
	}

	/**
	 * ユーザーアカウント作成時に使用するコンストラクタ
	 *
	 * @param id
	 * @param imageFileName
	 * @param image
	 * @throws IOException
	 */
	public ThumbnailEntity(Long id) {

		this.id = id;
		this.imageFileName = "thumbnail.jpg";
		this.image = getImageFileInAPP(this.imageFileName);
	}

	private byte[] getImageFileInAPP(String fileName) {

		final String FOLDER_PATH_1 = FolderName.SRC + FolderName.MAIN;
		final String FOLDER_PATH_2 = FolderName.RESOURCES + FolderName.STATIC + FolderName.IMAGE;
		final File file = new File(FOLDER_PATH_1 + FOLDER_PATH_2 + "/" + fileName);

		byte[] image = null;

		try (FileInputStream in = new FileInputStream(file)) {

			FileChannel channel = in.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
			channel.read(buffer);
			buffer.clear();
			image = new byte[buffer.capacity()];
			buffer.get(image);
			channel.close();

		} catch (Exception e) {
			System.out.println("Exception is:- " + e);
		}

		return image;
	}
}