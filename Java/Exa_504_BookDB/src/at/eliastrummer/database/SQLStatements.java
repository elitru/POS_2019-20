package at.eliastrummer.database;

public class SQLStatements {

    public static final String GET_BOOKS
            = "SELECT b.book_id,\n"
            + "		title, \n"
            + "		total_pages, \n"
            + "		rating, \n"
            + "		isbn, \n"
            + "		published_date,\n"
            + "		a.first_name AS \"authorFirstname\",\n"
            + "		a.middle_name AS \"authorMiddleName\",\n"
            + "		a.last_name AS \"authorLastname\",\n"
            + "		g.genre,\n"
            + "		p.name AS \"publisher\"\n"
            + "FROM books b\n"
            + "INNER JOIN book_genres bg ON bg.book_id = b.book_id\n"
            + "INNER JOIN genres g ON bg.genre_id = g.genre_id\n"
            + "INNER JOIN publishers p ON p.publisher_id = b.publisher_id\n"
            + "INNER JOIN book_authors ba ON ba.book_id = b.book_id\n"
            + "INNER JOIN authors a ON a.author_id = ba.author_id\n"
            + "WHERE UPPER(title) LIKE UPPER('%{title}%')\n"
            + "	AND UPPER(g.genre) LIKE UPPER('%{genre}%')\n"
            + "	AND UPPER(p.name) LIKE UPPER('%{publisher}%')\n"
            + "	AND (\n"
            + "		SELECT COUNT(*)\n"
            + "		FROM authors ath\n"
            + "		INNER JOIN book_authors ba1 ON ath.author_id = ba1.author_id\n"
            + "		WHERE ba1.book_id = b.book_id\n"
            + "			AND (UPPER(ath.last_name) LIKE UPPER('%{author_lastname}%') \n"
            + "				OR UPPER(ath.first_name) LIKE UPPER('%{author_firstname}%'))\n"
            + "	) > 0\n"
            + "ORDER BY title;";

    public static final String GET_PUBLISHERS = "SELECT DISTINCT p.name\n"
            + "FROM genres g\n"
            + "INNER JOIN book_genres bg ON bg.genre_id = g.genre_id\n"
            + "INNER JOIN books b ON bg.book_id = b.book_id\n"
            + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
            + "WHERE g.genre LIKE '%{genre}%';";

    public static final String GET_GENRES = "SELECT DISTINCT g.genre\n"
            + "FROM genres g\n"
            + "INNER JOIN book_genres bg ON bg.genre_id = g.genre_id\n"
            + "INNER JOIN books b ON bg.book_id = b.book_id\n"
            + "INNER JOIN publishers p ON b.publisher_id = p.publisher_id\n"
            + "WHERE p.name LIKE '%{publisher}%';";
}
