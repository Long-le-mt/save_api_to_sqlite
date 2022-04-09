package com.example.myapplication.view;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.dao.AppDatabase;
import com.example.myapplication.dao.DogDAO;
import com.example.myapplication.databinding.FragmentDetailBinding;
import com.example.myapplication.databinding.FragmentListsBinding;
import com.example.myapplication.model.DogBreed;
import com.example.myapplication.viewmodel.DogAdapter;
import com.example.myapplication.viewmodel.DogApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DogApiService dogApiService;
    private DogAdapter dogAdapter;
    private List<DogBreed> dogBreedLists;
    private List<DogBreed> dogBreedFilterLists;
    private FragmentListsBinding binding;
    private AppDatabase appDatabase;
    private DogDAO dogDAO;

    public ListsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListsFragment newInstance(String param1, String param2) {
        ListsFragment fragment = new ListsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        binding = FragmentListsBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = binding.getRoot();

        appDatabase = AppDatabase.getInstance(getActivity());
        dogDAO = appDatabase.dogDAO();

        dogBreedLists = new ArrayList<DogBreed>();
        dogBreedFilterLists = new ArrayList<>();
        dogAdapter = new DogAdapter(dogBreedLists);

        binding.rwDogs.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rwDogs.setAdapter(dogAdapter);

        dogApiService = new DogApiService();
        dogApiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(List<DogBreed> dogBreeds) {
                        dogDAO.deteleAll();
                        for(DogBreed dog : dogBreeds){
//                            Log.e("TEST API", dog.getName());
                            dogDAO.insertAll(dog);
                        }
//                        dogDAO.insertAll(dogBreeds);
                        dogBreedLists.clear();
                        dogBreedLists.addAll(dogBreeds);
                        dogBreedFilterLists.addAll(dogBreeds);
                        dogAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TEST API", e.getMessage());
                    }
                });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@androidx.annotation.NonNull Menu menu, @androidx.annotation.NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search dog");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<DogBreed> newDogs = new ArrayList<>();
                for (DogBreed dog: dogBreedFilterLists) {
                    if(dog.getName().contains(s) || s.isEmpty() || s == "")
                        newDogs.add(dog);
                }
                dogBreedLists.clear();
                dogBreedLists.addAll(newDogs);
                dogAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}