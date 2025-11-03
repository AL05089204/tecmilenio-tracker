package com.tecmilenio.imctracker.web.filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*; import java.io.IOException;
@WebFilter(filterName="AuthFilter", urlPatterns={
  "/dashboard", "/perfil", "/medicion/*", "/api/*"
})
public class AuthFilter implements Filter {
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    Long uid = (Long) req.getSession().getAttribute("authedUserId");
    if (uid == null) {
      if (req.getRequestURI().contains("/api/")) { resp.sendError(HttpServletResponse.SC_UNAUTHORIZED); return; }
      resp.sendRedirect(req.getContextPath()+"/login"); return;
    }
    chain.doFilter(request, response);
  }
}
