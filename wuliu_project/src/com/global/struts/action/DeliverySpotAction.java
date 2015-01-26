/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.global.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.global.dao.BaseDao;
import com.global.dao.DeliverySpotDao;
import com.global.struts.form.DeliverySpotForm;
import com.global.vo.City;
import com.global.vo.Deliveryspot;
import com.global.vo.Province;

/**
 * MyEclipse Struts Creation date: 11-24-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/deliverySpot" name="deliverySpotForm"
 *                input="/peiSongDianManage/addPeiSongDian.jsp" scope="request"
 *                validate="true"
 */
public class DeliverySpotAction extends BaseDispatchAction {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	/*
	 * 保存新配送点信息
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DeliverySpotForm deliverySpotForm = (DeliverySpotForm) form;
		String name = deliverySpotForm.getName().trim();
		String provinceID = request.getParameter("r");
		String cityID = request.getParameter("c");
		String tel = deliverySpotForm.getTel().trim();
		String startScope = deliverySpotForm.getStartScope().trim();
		String startPrice = deliverySpotForm.getStartPrice().trim();
		String secondPrice = deliverySpotForm.getSecondPrice().trim();
		String startvolumescope = deliverySpotForm.getStartvolumescope().trim();
		String startvolumeprice = deliverySpotForm.getStartvolumeprice().trim();
		String secondvolumeprice = deliverySpotForm.getSecondvolumeprice()
				.trim();

		BaseDao bdao = (BaseDao) super.getBean("BaseDao");
		DeliverySpotDao dao = (DeliverySpotDao) super
				.getBean("DeliverySpotDao");

		Province p = (Province) bdao.get(Province.class,
				new Integer(provinceID));
		City c = (City) bdao.get(City.class, new Integer(cityID));
		if (dao.checkDSName(name)) {
			String msg = "<font color=red><b>×</b></font>";
			request.setAttribute("msg", msg);
			return mapping.getInputForward();
		} else {
			Deliveryspot ds = new Deliveryspot();

			ds.setCity(c);
			ds.setName(name);
			ds.setTel(tel);
			ds.setProvince(p);
			ds.setStartscope(Double.parseDouble(startScope));
			ds.setStartprice(Double.parseDouble(startPrice));
			ds.setSecondprice(Double.parseDouble(secondPrice));
			ds.setStartvolumescope(Double.parseDouble(startvolumescope));
			ds.setStartvolumeprice(Double.parseDouble(startvolumeprice));
			ds.setSecondvolumeprice(Double.parseDouble(secondvolumeprice));

			ds.setFlag(0);
			bdao.save(ds);
			return listAllDeliveryspot(mapping, form, request, response);
		}
	}

	/*
	 * 分页列出所有的配送点信息，用于查看
	 */
	public ActionForward listAllDeliveryspot(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		DeliverySpotDao dao = (DeliverySpotDao) super
				.getBean("DeliverySpotDao");

		String offset = request.getParameter("pager.offset");
		int intOffset = 0;

		if (offset != null) {
			intOffset = new Integer(offset).intValue();
		}
		int count = dao.countOfAllDeliveryspot();

		List list = dao.queryAllDeliverySpot(intOffset, 10);
		request.setAttribute("AllDeliveryspotList", list);
		request.setAttribute("count", count);

		return mapping.findForward("viewPeiSongDian");
	}

	/*
	 * 删除配送点,置flag为1
	 */
	public ActionForward deletePeiSongDian(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String[] dsids = request.getParameterValues("dsids");
		DeliverySpotDao dao = (DeliverySpotDao) super
				.getBean("DeliverySpotDao");

		for (int i = 0; i < dsids.length; i++) {
			Deliveryspot ds = dao
					.queryDeliverySpotByDeliverySpotID(new Integer(dsids[i]));

			ds.setFlag(1);

			dao.updateDeliverySpot(ds);
		}
		return listAllDeliveryspot(mapping, form, request, response);
	}

	/*
	 * 查看某个配送点的信息
	 */
	public ActionForward viewPeiSongDian(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		DeliverySpotDao dao = (DeliverySpotDao) super
				.getBean("DeliverySpotDao");
		Deliveryspot ds = dao
				.queryDeliverySpotByDeliverySpotID(new Integer(id));
		request.setAttribute("Deliveryspot", ds);
		return mapping.findForward("viewPeiSongDianInfo");
	}

	/*
	 * 修改某个配送点信息时，根据ID显示其具体信息
	 */
	public ActionForward modifyPeiSongDian(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		DeliverySpotDao dao = (DeliverySpotDao) super
				.getBean("DeliverySpotDao");
		Deliveryspot ds = dao
				.queryDeliverySpotByDeliverySpotID(new Integer(id));
		request.setAttribute("Deliveryspot", ds);
		return mapping.findForward("modifyPeiSongDianInfo");
	}

	/*
	 * 保存已修改的配送点信息
	 */
	public ActionForward updatePeiSongDian(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name").trim();
		String tel = request.getParameter("tel").trim();
		String startScope = request.getParameter("startScope").trim();
		String startPrice = request.getParameter("startPrice").trim();
		String secondPrice = request.getParameter("secondPrice").trim();

		String startvolumescope = request.getParameter("startvolumescope")
				.trim();
		String startvolumeprice = request.getParameter("startvolumeprice")
				.trim();
		String secondvolumeprice = request.getParameter("secondvolumeprice")
				.trim();

		DeliverySpotDao dao = (DeliverySpotDao) super
				.getBean("DeliverySpotDao");
		Deliveryspot ds = dao
				.queryDeliverySpotByDeliverySpotID(new Integer(id));

		ds.setName(name);
		ds.setTel(tel);
		ds.setStartscope(new Double(startScope));
		ds.setStartprice(new Double(startPrice));
		ds.setSecondprice(new Double(secondPrice));
		ds.setStartvolumescope(Double.parseDouble(startvolumescope));
		ds.setStartvolumeprice(Double.parseDouble(startvolumeprice));
		ds.setSecondvolumeprice(Double.parseDouble(secondvolumeprice));
		System.out.println("---------");
		dao.updateDeliverySpot(ds);
		return listAllDeliveryspot(mapping, form, request, response);
	}

	// 检验配送点是否有重名
	public ActionForward checkDSName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String name = null;
		try {
			name = new String(request.getParameter("name").getBytes(
					"ISO-8859-1"), "gbk");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DeliverySpotDao dao = (DeliverySpotDao) super
				.getBean("DeliverySpotDao");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!name.equals("")) {
			if (name.length() < 20 && name.length() > 0) {
				if (dao.checkDSName(name)) {
					String msg = "<font color=red><b>×</b></font>";
					out.print(msg);
				} else {
					String msg = "<font color=green><b>√</b></font>";
					out.print(msg);
				}
			} else {
				String msg = "<font color=red><b>×</b></font>";
				out.print(msg);
			}
		} else {
			String msg = "<font color=red><b>×</b></font>";
			out.print(msg);
		}
		return null;
	}
}