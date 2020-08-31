package xml.team7.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xml.team7.zuul.client.UserClient;
import xml.team7.zuul.dto.RoleDTO;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private UserClient userClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (request.getHeader("AuthToken") == null) {
            return null;
        }
        String token = request.getHeader("AuthToken").substring(7);

        try {
            RoleDTO roleDTO = this.userClient.verify(token);
            StringBuilder roles = new StringBuilder();

            roleDTO.getRoles().forEach(s -> roles.append(s).append("-"));
            if (roleDTO.getPrivileges() != null)
                roleDTO.getPrivileges().forEach(s -> roles.append(s).append("-"));

            ctx.addZuulRequestHeader("Username", roleDTO.getUsername());
            ctx.addZuulRequestHeader("Roles", String.valueOf(roles));

        } catch (FeignException.NotFound e) {
            setFailedRequest("User does not exist", 403);
        }
        return null;
    }
}
