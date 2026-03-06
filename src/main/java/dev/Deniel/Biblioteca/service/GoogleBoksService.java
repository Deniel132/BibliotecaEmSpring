package dev.Deniel.Biblioteca.service;

import dev.Deniel.Biblioteca.dto.GoogleBooksDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class GoogleBoksService {


	private final RestTemplate restTemplate;

	@Value("${google.books.key}")
	private String apiKey;

	private final String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes";


	public GoogleBoksService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;

	}

	public GoogleBooksDTO.GoogleBooksResponse buscarPorIsbn(String isbn) {
		String url = UriComponentsBuilder.fromUri(URI.create(GOOGLE_BOOKS_URL))
				.queryParam("q", "isbn:" + isbn)
				.queryParam("key", apiKey)
				.toUriString();

		try {
			return restTemplate.getForObject(url, GoogleBooksDTO.GoogleBooksResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao consultar a API do Google Books: " + e.getMessage());
		}
	}

	public String getImagemCapa(GoogleBooksDTO.VolumeInfo volumeInfo) {
		if (volumeInfo == null || volumeInfo.getImageLinks() == null) return "https://imgs.search.brave.com/rE4ZyYjc3QDVKl-gftgrHKlsitRsCnamWzMxwx7qSaE/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pczIt/c3NsLm16c3RhdGlj/LmNvbS9pbWFnZS90/aHVtYi9QdWJsaWNh/dGlvbjQ5L3Y0LzJh/LzY2L2JiLzJhNjZi/YjY0LTEzOTktMzU1/Zi01ZjNlLWI0NzBi/NjU5MDJlZC85Nzg4/NTgwNTc5MzIxLmpw/Zy8zOTB4MzkwYmIu/anBn";

		GoogleBooksDTO.ImageLinks img = volumeInfo.getImageLinks();
		if (img.getSmallThumbnail() != null) return img.getSmallThumbnail();
		if (img.getThumbnail() != null) return img.getThumbnail();
		if (img.getSmall() != null) return img.getSmall();
		return "https://imgs.search.brave.com/rE4ZyYjc3QDVKl-gftgrHKlsitRsCnamWzMxwx7qSaE/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pczIt/c3NsLm16c3RhdGlj/LmNvbS9pbWFnZS90/aHVtYi9QdWJsaWNh/dGlvbjQ5L3Y0LzJh/LzY2L2JiLzJhNjZi/YjY0LTEzOTktMzU1/Zi01ZjNlLWI0NzBi/NjU5MDJlZC85Nzg4/NTgwNTc5MzIxLmpw/Zy8zOTB4MzkwYmIu/anBn";
	}

}






