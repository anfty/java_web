<%@ page contentType="text/html;charset=utf8" %>
<%@ page import="tom.bean.ShowByPage" %>
<jsp:useBean id="show" class="tom.bean.ShowByPage" scope="session"/>
<HTML>
<Body >
<jsp:include page="head.html" flush="true"/>
<center class="myset">
<P>显示会员信息。
 <BR>每页最多显示<jsp:getProperty  name= "show"  property="pageSize"  />条信息。
 <BR>当前显示第<Font color=blue>
     <jsp:getProperty  name= "show"  property="showPage"  />
   </Font>页,共有
   <Font color=blue><jsp:getProperty  name= "show"  property="pageAllCount"  />
   </Font>页。
<BR>当前显示的内容是：
  <table border=2>
  <tr>
    <th>name</th><th>sex</th><th>age</th>
    <th>phone</th><th>emial</th><th>esay</th><th>pic</th>
  </tr>
  <jsp:getProperty  name= "show"  property="presentPageResult" />
  </table>
<BR>单击“前一页”或“后一页”按钮查看信息
 <Table>
  <tr><td><FORM action="helpShowMember" method="post">
          <Input type=hidden name="showPage" value="<%=show.getShowPage()-1 %>" >
           <Input type=submit name="g" value="前一页">
          </FORM>
      </td>
      <td><FORM action="helpShowMember" method="post">
          <Input type=hidden name="showPage" value="<%=show.getShowPage()+1 %>" >
          <Input type=submit name="g" value="后一页">
          </Form>
      </td>
      <td> <FORM action="helpShowMember" method="post">
           输入页码：<Input type=text name="showPage" size=5 >
           <Input type=submit name="g" value="提交">
           </FORM>
      </td>
  </tr>
  </Table>
</Center>
</BODY></HTML>
