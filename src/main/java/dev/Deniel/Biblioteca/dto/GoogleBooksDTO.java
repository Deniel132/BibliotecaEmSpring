package dev.Deniel.Biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class GoogleBooksDTO {

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	public static class GoogleBooksResponse{
		private Integer totalItems;
		private List<Item> items;
	}


	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Item {
		private VolumeInfo volumeInfo;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class VolumeInfo{
		private String title;
		private List<String> authors;
		private ImageLinks imageLinks;
	}

	@Data
	public static class ImageLinks{
		public String smallThumbnail;
		public String thumbnail;
		public String small;
		public String medium;
		public String large;
		public String extraLarge;
	}


}
