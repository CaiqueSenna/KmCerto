package com.caiquesenna.kmcerto.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.caiquesenna.kmcerto.R
import com.caiquesenna.kmcerto.databinding.ActivityDashboardBinding
import com.caiquesenna.kmcerto.utils.CurrencyUtils
import com.caiquesenna.kmcerto.viewmodel.DashboardViewModel

class Dashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupUI()
        setupObservers()
        setupClickListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupUI() {
        // Resume Cards
        setupStatCard(binding.cardVehicles.root, "Veículos", R.drawable.ic_car, R.color.md_theme_primary)
        setupStatCard(binding.cardFuelings.root, "Abastecimentos", R.drawable.ic_gas_station, R.color.md_theme_secondary)
        setupStatCard(binding.cardOilChanges.root, "Trocas de Óleo", R.drawable.ic_oil, R.color.md_theme_tertiary)
        setupStatCard(binding.cardMaintenance.root, "Manutenções", R.drawable.ic_maintenance, android.R.color.holo_purple)
        setupStatCard(binding.cardMonthCost.root, "Gasto no Mês", R.drawable.ic_cost, android.R.color.holo_green_dark)
        setupStatCard(binding.cardYearCost.root, "Gasto no Ano", R.drawable.ic_cost, android.R.color.holo_red_dark)

        // Menu Buttons
        setupMenuButton(binding.btnVehicles.root, "Veículos", R.drawable.ic_car)
        setupMenuButton(binding.btnFuelings.root, "Abastec.", R.drawable.ic_gas_station)
        setupMenuButton(binding.btnOilChanges.root, "Óleo", R.drawable.ic_oil)
        setupMenuButton(binding.btnMaintenance.root, "Manutenção", R.drawable.ic_maintenance)
        setupMenuButton(binding.btnReports.root, "Relatórios", R.drawable.ic_reports)
        setupMenuButton(binding.btnSettings.root, "Config.", R.drawable.ic_settings)
    }

    private fun setupStatCard(view: View, label: String, iconRes: Int, colorRes: Int) {
        view.findViewById<TextView>(R.id.tvStatLabel).text = label
        val icon = view.findViewById<ImageView>(R.id.ivStatIcon)
        icon.setImageResource(iconRes)
        icon.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, colorRes))
    }

    private fun setupMenuButton(view: View, label: String, iconRes: Int) {
        view.findViewById<TextView>(R.id.tvMenuLabel).text = label
        view.findViewById<ImageView>(R.id.ivMenuIcon).setImageResource(iconRes)
    }

    private fun setupObservers() {
        viewModel.vehicleCount.observe(this) { count ->
            binding.cardVehicles.root.findViewById<TextView>(R.id.tvStatValue).text = count.toString()
        }
        viewModel.fuelingCount.observe(this) { count ->
            binding.cardFuelings.root.findViewById<TextView>(R.id.tvStatValue).text = count.toString()
            binding.cardFuelings.root.findViewById<TextView>(R.id.tvStatValue).setTextColor(
                ContextCompat.getColor(this, R.color.md_theme_secondary)
            )
        }
        viewModel.oilChangeCount.observe(this) { count ->
            binding.cardOilChanges.root.findViewById<TextView>(R.id.tvStatValue).text = count.toString()
            binding.cardOilChanges.root.findViewById<TextView>(R.id.tvStatValue).setTextColor(
                ContextCompat.getColor(this, R.color.md_theme_tertiary)
            )
        }
        viewModel.maintenanceCount.observe(this) { count ->
            binding.cardMaintenance.root.findViewById<TextView>(R.id.tvStatValue).text = count.toString()
            binding.cardMaintenance.root.findViewById<TextView>(R.id.tvStatValue).setTextColor(
                ContextCompat.getColor(this, android.R.color.holo_purple)
            )
        }
        viewModel.monthCost.observe(this) { cost ->
            binding.cardMonthCost.root.findViewById<TextView>(R.id.tvStatValue).text = 
                CurrencyUtils.format(cost ?: 0.0)
        }
        viewModel.yearCost.observe(this) { cost ->
            binding.cardYearCost.root.findViewById<TextView>(R.id.tvStatValue).text = 
                CurrencyUtils.format(cost ?: 0.0)
        }
    }

    private fun setupClickListeners() {
        binding.btnVehicles.root.setOnClickListener {
            startActivity(Intent(this, VehicleListActivity::class.java))
        }
        binding.btnFuelings.root.setOnClickListener {
            startActivity(Intent(this, FuelingListActivity::class.java))
        }
        binding.btnOilChanges.root.setOnClickListener {
            startActivity(Intent(this, OilChangeListActivity::class.java))
        }
        binding.btnMaintenance.root.setOnClickListener {
            startActivity(Intent(this, MaintenanceListActivity::class.java))
        }
        binding.btnReports.root.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }
        binding.btnSettings.root.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}