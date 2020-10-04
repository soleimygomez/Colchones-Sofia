package com.bean.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import com.entity.other.*;
import com.dao.*;

/**
 * Implementation ChartJsBean.
 * 
 * @author DeveUp.
 * @phone 3118398189.
 * @email deveup@gmail.com.
 * @version 1.0.0.0.
 */
@ManagedBean(name = "chartjs")
@RequestScoped
public class ChartJsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DonutChartModel donut_vendedor_venta_genero;
	private Color colores;

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public ChartJsBean() {
	}

	///////////////////////////////////////////////////////
	// Post
	///////////////////////////////////////////////////////
	@PostConstruct
	public void init() {
		this.colores = new Color();
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////

	///////////////////////////////////////////////////////
	// Method - DonutChartModel
	///////////////////////////////////////////////////////
	/**
	 * Metodo que permite saber cuantas persona por sexo trabajan en la empresa.
	 */
	public void donutVendedorVentaGenero() {
		// INIT
		donut_vendedor_venta_genero = new DonutChartModel();
		ChartData data = new ChartData();
		int index = 0;
		DonutChartDataSet dataSet = new DonutChartDataSet();

		// SQL
		VendedorDao dao = new VendedorDao();
		List<ChartJS> list = dao.cantidadGenero();

		// LIST
		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();

		// ITERRAR
		for (ChartJS c : list) {
			values.add(c.getCantidad());
			labels.add(Convertidor.genero(c.getNombre()));
			bgColors.add(this.colores.getColores().get(index));
			index++;
		}

		// ADD LIST
		data.setLabels(labels);
		dataSet.setData(values);
		dataSet.setBackgroundColor(bgColors);

		// ADD
		data.addChartDataSet(dataSet);
		donut_vendedor_venta_genero.setData(data);
	}

	///////////////////////////////////////////////////////
	// Renderizar - DonutChartModel
	///////////////////////////////////////////////////////

	public DonutChartModel getDonut_vendedor_venta_genero() {
		this.donutVendedorVentaGenero();
		return donut_vendedor_venta_genero;
	}

	///////////////////////////////////////////////////////
	// Getter y Setters
	///////////////////////////////////////////////////////

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setDonut_vendedor_venta_genero(DonutChartModel donut_vendedor_venta_genero) {
		this.donut_vendedor_venta_genero = donut_vendedor_venta_genero;
	}

	public Color getColores() {
		return colores;
	}

	public void setColores(Color colores) {
		this.colores = colores;
	}
}
