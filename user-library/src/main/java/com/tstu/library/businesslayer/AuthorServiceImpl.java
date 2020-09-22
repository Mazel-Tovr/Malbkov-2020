package com.tstu.library.businesslayer;

import com.tstu.library.businesslayer.interfaces.IAuthorService;
import com.tstu.library.dao.AuthorAccesses;
import com.tstu.library.dao.interfaces.IAuthorOperations;
import com.tstu.library.exception.DataException;
import com.tstu.library.model.Author;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import static com.tstu.library.exception.ExceptionsCods.*;

public class AuthorServiceImpl implements IAuthorService {

    private IAuthorOperations authorTable = new AuthorAccesses();

    private static final Logger logger = Logger.getLogger("BusinessLayerLogger");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\s*((?:19|20)\\d{2})\\-(1[012]|0?[1-9])\\-(3[01]|[12][0-9]|0?[1-9])\\s*$");

    @Override
    public List<Author> getAll() throws DataException {
        List<Author> authors = authorTable.getAll();
        if (authors.isEmpty())
            throw new DataException("authors are absent",EMPTY_RESULT);
        logger.info("Authors were gotten");
        return authors;
    }

    @Override
    public Author getAuthor(int authorId) throws DataException {
        Author author =  authorTable.getEntity(authorId);
        if(author == null) throw new DataException("Author with such id doesn't exist",AUTHOR_DOES_NOT_EXIST);
        logger.info("Author was get");
        return author;
    }

    @Override
    public void addAuthor(String name, String secondName, String lastName, String dob) throws DataException {
        if(name.isEmpty() || secondName.isEmpty())
            throw new DataException("Empty input",EMPTY_FIELD_INPUT);
        String  date = dob;
        if(!DATE_PATTERN.matcher(dob).matches()) {
            try {
                date =  new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd.mm.yyyy").parse(dob));
            } catch (ParseException e) {
                throw new DataException("Wrong date format",WRONG_DATE_FORMAT);//TODO tryParse to normal format (if left some time (ofc no))
            }
        }
        int maxId = authorTable.getMaxIdPlusOne();
        if(maxId < 0) throw new DataException("Max id less then 0",ENTITY_CUN_NOT_BE_ADDED_MAX_ID_NOT_RECEIVED);

        authorTable.addNewEntity(new Author(maxId,name,secondName,lastName,Date.valueOf(date)));
        logger.info("Author was add");
    }

    @Override
    public void dropAuthorAndAllHisBooks(int authorId) throws DataException {

     Author author =  authorTable.getEntity(authorId);
     if(author == null) throw new DataException("Author with such id doesn't exist",AUTHOR_DOES_NOT_EXIST);
     authorTable.deleteAuthorAndAllHisBooks(authorId);
     logger.info("Author  deleted");
    }

    @Override
    public Author getByNameSecondNameLastNameDob(String name, String secondName, String lastName, String dob) throws DataException {
        String date = dob;
        if(!DATE_PATTERN.matcher(dob).matches()) {
            try {
                //Switch to normalFormat
                date =  new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd.mm.yyyy").parse(dob));

            } catch (ParseException e) {
                throw new DataException("Wrong date format",WRONG_DATE_FORMAT);
            }
        }
        return authorTable.isAuthorExist(new Author(name,secondName,lastName,Date.valueOf(date)));
    }

    @Override
    public void editing(int authorId, String name, String secondName, String lastName, String dob) throws DataException {
        if(name.isEmpty() || secondName.isEmpty())
            throw new DataException("Empty input",EMPTY_FIELD_INPUT);
        String  date = dob;
        if(!DATE_PATTERN.matcher(dob).matches()) {
            try {
                date =  new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd.mm.yyyy").parse(dob));
            } catch (ParseException e) {
                throw new DataException("Wrong date format",WRONG_DATE_FORMAT);//TODO tryParse to normal format (if left some time (ofc no))
            }
        }

        authorTable.editing(authorId,new Author(name,secondName,lastName,Date.valueOf(date)));
        logger.info("Author edited");
    }


}
