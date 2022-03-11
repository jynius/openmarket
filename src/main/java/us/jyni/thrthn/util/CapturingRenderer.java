/*
 * 
 */
package us.jyni.thrthn.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author jynius
 * @Since 2020-11-07
 */
@Component
public class CapturingRenderer implements ServletContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(CapturingRenderer.class);
	
	private static final String PREFIX = "/WEB-INF/jsp/";
	private static final String SUFFIX = ".jsp";

	public class CapturingHttpServletRequest extends MockHttpServletRequest {
		
		public CapturingHttpServletRequest() {
			super();
			setMethod(HttpMethod.GET.name());
		}
		
		@Override
		public DispatcherType getDispatcherType() {
			return DispatcherType.INCLUDE;
		}
		
		@Override
		public boolean isAsyncSupported() {
			return false;
		}
	}

	@Data
	@EqualsAndHashCode(callSuper = true)
	@ToString(callSuper = false)
	public class CapturingHttpServletResponse extends HttpServletResponseWrapper implements HttpServletResponse {

		private String characterEncoding;
		private String contentType = null;
		private Locale locale = Locale.getDefault();
		private PrintWriter writer;
		private ServletOutputStream outputStream;
		
		private int bufferSize = 0;
		private boolean committed = false;
		private String errorMessage = null;
		private String redirectUrl = null;
		private int status = HttpServletResponse.SC_OK;
		
		public CapturingHttpServletResponse(StringWriter writer, Charset charset) {
			super(new MockHttpServletResponse());
			this.writer = new PrintWriter(writer);
			this.outputStream = new WriterOutputStream(writer, charset);
		}
	}

	@Data
	@EqualsAndHashCode(callSuper = true)
	@ToString(callSuper = false)
	public class WriterOutputStream extends ServletOutputStream {

		private Charset charset;
		private Writer writer;
		private byte[] buffer = new byte[1];
		
		public WriterOutputStream(Writer writer, Charset charset) {
			this.writer = writer;
			this.charset = charset;
		}

		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void write(int b) throws IOException {
			buffer[0] = (byte) b;
			write(buffer);
		}

		@Override
		public void setWriteListener(WriteListener writeListener) {
			// TODO Auto-generated method stub

		}
	}
	
	@Resource
	private LocaleResolver localeResolver;
	private ServletContext servletContext;
	
	public String render(String viewName, Model model, Locale locale) {
		
		HttpServletRequest request = new CapturingHttpServletRequest();
		addModelAsRequestAttributes(request, model);
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, localeResolver);
		request.setAttribute(Config.FMT_LOCALIZATION_CONTEXT + ".request", new LocalizationContext(null, locale));
		request.setAttribute(Config.FMT_LOCALE, locale);

		StringWriter writer = new StringWriter();
		HttpServletResponse response = new CapturingHttpServletResponse(writer, Charset.forName("UTF-8"));
		response.setContentType("text/html;charset=utf-8");
		
		try {
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher(path(viewName));
			dispatcher.include(request, response);
		}
		catch (ServletException | IOException e) {
			LOG.error("occur error at view[{}]: {}", viewName, e);
			throw new IllegalArgumentException("occur error at view[" + viewName + "]");
		}
		
		return writer.getBuffer().toString();
	}
	
	private void addModelAsRequestAttributes(HttpServletRequest request, Model model) {

		if(request==null || model==null) {
			return;
		}
		
		for(Map.Entry<String, Object> entry: model.asMap().entrySet()) {
			
			Object value = entry.getValue();
			if(value==null) {
				request.removeAttribute(entry.getKey());
			}
			else {
				request.setAttribute(entry.getKey(), value);
			}
		}
	}

	private String path(String viewName) {
		return String.format("%s%s%s", PREFIX, viewName, SUFFIX);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
