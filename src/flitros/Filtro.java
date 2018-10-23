package flitros;

import java.io.IOException;
import java.util.HashMap;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
@WebFilter(filterName="/FiltroEstadistica",urlPatterns="/*" )
public class Filtro implements Filter {
 
 public void destroy() {
 // TODO Auto-generated method stub
 }
 @SuppressWarnings("unchecked")
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 // TODO Auto-generated method stub
 // place your code here
 
HttpServletRequest peticion= (HttpServletRequest)request;
 ServletContext contexto= request.getServletContext();
 
 HashMap<String, Integer> urls;
 if (contexto.getAttribute("estadistica")==null) {
 //creamos un objeto en el contexto
 urls= new HashMap<String,Integer>();
 
 urls.put(peticion.getRequestURL().toString(), 1);
 contexto.setAttribute("estadistica", urls);
 }else {
 
 // actualizamos claves e incrementamos
 urls=(HashMap<String,Integer>)contexto.getAttribute("estadistica");
 
 if (urls.get(peticion.getRequestURL().toString())==null) {
 
 urls.put(peticion.getRequestURL().toString(), 1);
 }else {
 urls.put(peticion.getRequestURL().toString(), urls.get(peticion.getRequestURL().toString())+1);
 }
 }
 
 chain.doFilter(request, response);
 }
 
/**
 * @see Filter#init(FilterConfig)
 */
 public void init(FilterConfig fConfig) throws ServletException {
 // TODO Auto-generated method stub
 }
 
}