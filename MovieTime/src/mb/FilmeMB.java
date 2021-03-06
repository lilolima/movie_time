package mb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import dao.FilmeDAO;
import dao.FilmeDAOImpl;
import entidade.Filme;

@ManagedBean
@SessionScoped
public class FilmeMB {
	
	private String nome;
	private List<Filme> filmes = new ArrayList<Filme>();
	private List<Filme> watchlist = new ArrayList<Filme>();
	private Filme watchAtual = new Filme();
	private Filme filmeAtual = new Filme();
	private FilmeDAO filmeDAO = new FilmeDAOImpl();
	
	
	public FilmeMB() {
		this.filmes = new ArrayList<Filme>();
		this.filmeAtual= new Filme();
		this.watchlist = new ArrayList<Filme>();
		this.watchAtual= new Filme();
	}
	
	
	public void pesquisar() { 
	System.out.println("Pressionado o buscar para o filme: " + this.nome);
   	 this.nome = this.nome.replaceAll(" ", "+");
   	 //filmeAtual.setNome(nome.replaceAll(" ", "+"));
   	 JSONParser parser = new JSONParser();

        try {        
            URL oracle = new URL("http://www.omdbapi.com/?t="+this.nome+"&y=&plot=short&r=json"); // URL to Parse
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
           
            String inputLine;
            while ((inputLine = in.readLine()) != null) {              
                JSONObject obj = (JSONObject) parser.parse(inputLine);
               
                filmeAtual.setNomeFilme((String) obj.get("Title")); 
                filmeAtual.setSinopse( (String) obj.get("Plot"));  
                //URL url = new URL((String) obj.get("Poster"));
                //image = ImageIO.read(url);
                filmeAtual.setImagem( (String) obj.get("Poster")); 
                filmeAtual.setAno( (String) obj.get("Year")); 
                filmeAtual.setGenero( (String) obj.get("Genre")); 
                filmeAtual.setRate( (String) obj.get("imdbRating")); 
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        filmes.add(filmeAtual);
        filmeAtual = new Filme();
    	System.out.println("A lista cont�m " + filmes.size() + " elementos" );
    	//return "";
		//filmes = timeDAO.pesquisarPorNome( timeAtual.getNome() );
		//return "";
	}
	
	public String adicionar() { 
		/* this.wachtlist = new ArrayList<Watch>();
		
		System.out.println("Pressionado o Add na lista para o filme: " + this.nome);
		filmes.add(filmeAtual);
		filmeAtual = new Filme();
		System.out.println("A lista cont�m " + filmes.size() + " elementos");
		return "";*/
		filmes = watchlist; //lista
		filmeAtual = watchAtual; //obj
		filmeDAO.adicionar( watchAtual);
		filmeAtual = new Filme();
		return "";
	}
	
	public Filme getFilmeAtual() {
		return filmeAtual;
	}
	public void setFilmeAtual(Filme FilmeAtual) {
		this.filmeAtual = FilmeAtual;
	}
	
	public List<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes(List<Filme> Filmes) {
		this.filmes = Filmes;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
