package at.EliasTrummer.ZodiacSign.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import at.EliasTrummer.ZodiacSign.R;
import at.EliasTrummer.ZodiacSign.ZodiacHolder;

public class ZodiacAdapter extends RecyclerView.Adapter<ZodiacHolder> {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM");

    private List<ZodiacSign> signs = new ArrayList<ZodiacSign>(){{
        add(new ZodiacSign("Wassermann", MonthDay.parse("21.01", dtf), R.drawable.aquarius));
        add(new ZodiacSign("Fisch", MonthDay.parse("20.02", dtf), R.drawable.pisces));
        add(new ZodiacSign("Widder", MonthDay.parse("21.03", dtf), R.drawable.aries));
        add(new ZodiacSign("Stier", MonthDay.parse("21.04", dtf), R.drawable.taurus));
        add(new ZodiacSign("Zwillinge", MonthDay.parse("21.05", dtf), R.drawable.gemini));
        add(new ZodiacSign("Krebs", MonthDay.parse("22.06", dtf), R.drawable.cancer));
        add(new ZodiacSign("Löwe", MonthDay.parse("23.07", dtf), R.drawable.leo));
        add(new ZodiacSign("Jungfrau", MonthDay.parse("24.08", dtf), R.drawable.virgo));
        add(new ZodiacSign("Waage", MonthDay.parse("24.09", dtf), R.drawable.libra));
        add(new ZodiacSign("Skorpion", MonthDay.parse("24.10", dtf), R.drawable.scorpius));
        add(new ZodiacSign("Schütze", MonthDay.parse("23.11", dtf), R.drawable.sagittarius));
        add(new ZodiacSign("Steinbock", MonthDay.parse("22.12", dtf), R.drawable.capricornus));
    }};

    @NonNull
    @Override
    public ZodiacHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zodiac_item, parent, false);

        ImageView ivZodiac = view.findViewById(R.id.ivZodiac);
        TextView tvZodiacName = view.findViewById(R.id.tvZodiaName),
                tvZodiacDescription = view.findViewById(R.id.tvZodiacDesciption);

        return new ZodiacHolder(view, view.findViewById(R.id.zodiacItem), ivZodiac, tvZodiacName, tvZodiacDescription);
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacHolder holder, int position) {
        ZodiacSign zodiac = signs.get(position);

        holder.getIvZodiac().setImageResource(zodiac.getDrawableId());
        holder.getTvZodiacName().setText(zodiac.getName());

        String desc = zodiac.getStartDateString() + " bis ";
        ZodiacSign next = signs.get(((position + 1) % signs.size()));
        desc += ZodiacSign.dtf.format(next.getStartDate().withDayOfMonth(next.getStartDate().getDayOfMonth() - 1));

        holder.getTvZodiacDescription().setText(desc);
    }

    @Override
    public int getItemCount() {
        return signs.size();
    }
}
