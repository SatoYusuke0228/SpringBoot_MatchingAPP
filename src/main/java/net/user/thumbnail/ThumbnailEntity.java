package net.user.thumbnail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "THUMBNAIL_TABLE")
public class ThumbnailEntity {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "IMAGE_FILE_NAME")
	private String imageFileName;

	@Column(name = "IMAGE")
	private byte[] image;

	/**
	 *
	 */
	public ThumbnailEntity() {
	}

	/**
	 * ユーザーアカウント作成時に使用するコンストラクタ
	 *
	 * @param id
	 * @param imageFileName
	 * @param image
	 */
	public ThumbnailEntity(Long id) {

		this.id = id;
		this.imageFileName = "hogehoge";
		this.image = null;
	}
}